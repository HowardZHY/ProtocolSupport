/*
 * This file is part of ViaVersion - https://github.com/ViaVersion/ViaVersion
 * Copyright (C) 2016-2025 ViaVersion and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.viaversion.viaversion;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.platform.PlatformTask;
import com.viaversion.viaversion.api.platform.UnsupportedSoftware;
import com.viaversion.viaversion.api.platform.ViaPlatform;
import com.viaversion.viaversion.bukkit.commands.BukkitCommandHandler;
import com.viaversion.viaversion.bukkit.listeners.JoinListener;
import com.viaversion.viaversion.bukkit.platform.BukkitViaAPI;
import com.viaversion.viaversion.bukkit.platform.BukkitViaConfig;
import com.viaversion.viaversion.bukkit.platform.BukkitViaInjector;
import com.viaversion.viaversion.bukkit.platform.BukkitViaLoader;
import com.viaversion.viaversion.bukkit.platform.BukkitViaTask;
import com.viaversion.viaversion.dump.PluginInfo;
import com.viaversion.viaversion.libs.gson.JsonObject;
import com.viaversion.viaversion.unsupported.UnsupportedPlugin;
import com.viaversion.viaversion.util.GsonUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("rawtypes")
public class ViaVersionPlugin extends JavaPlugin implements ViaPlatform<Player> {

    private static final Runnable DUMMY_RUNNABLE = () -> {};

    private static ViaVersionPlugin instance;

    private final BukkitCommandHandler commandHandler = new BukkitCommandHandler();

    private final BukkitViaConfig conf;

    private final ViaAPI<Player> api = new BukkitViaAPI();

    private boolean lateBind;

    public static boolean protocolSupport;

    public ViaVersionPlugin() {
        instance = this;

        conf = new BukkitViaConfig(getDataFolder(), getLogger());
        Via.init(ViaManagerImpl.builder()
                .platform(this)
                .commandHandler(commandHandler)
                .injector(new BukkitViaInjector())
                .loader(new BukkitViaLoader(this))
                .build());

        conf.reload();
    }

    @Override
    public void onLoad() {
        protocolSupport = Bukkit.getPluginManager().getPlugin("ProtocolSupport") != null;
        lateBind = !((BukkitViaInjector) Via.getManager().getInjector()).isBinded();

        if (!lateBind) {
            getLogger().info("ViaVersion " + getDescription().getVersion() + " is now loaded. Registering protocol transformers and injecting...");
            ((ViaManagerImpl) Via.getManager()).init();
        } else {
            getLogger().info("ViaVersion " + getDescription().getVersion() + " is now loaded. Waiting for boot (late-bind).");
        }
    }

    @Override
    public void onEnable() {
        final ViaManagerImpl manager = (ViaManagerImpl) Via.getManager();
        if (lateBind) {
            getLogger().info("Registering protocol transformers and injecting...");
            manager.init();
        }

        if (Via.getConfig().shouldRegisterUserConnectionOnJoin()) {
            // When event priority ties, registration order is used.
            // Must register without delay to ensure other plugins on lowest get the fix applied.
            getServer().getPluginManager().registerEvents(new JoinListener(), this);
        }

        if (Via.getManager().getInjector().lateProtocolVersionSetting()) {
            // Enable after server has loaded at the next tick
            runSync(manager::onServerLoaded);
        } else {
            manager.onServerLoaded();
        }

        getCommand("viaversion").setExecutor(commandHandler);
        getCommand("viaversion").setTabCompleter(commandHandler);
    }

    @Override
    public void onDisable() {
        ((ViaManagerImpl) Via.getManager()).destroy();
    }

    @Override
    public String getPlatformName() {
        return Bukkit.getServer().getName();
    }

    @Override
    public String getPlatformVersion() {
        return Bukkit.getServer().getVersion();
    }

    @Override
    public String getPluginVersion() {
        return getDescription().getVersion();
    }

    @Override
    public PlatformTask runAsync(Runnable runnable) {
        return new BukkitViaTask(getServer().getScheduler().runTaskAsynchronously(this, runnable));
    }

    @Override
    public PlatformTask runRepeatingAsync(final Runnable runnable, final long ticks) {
        return new BukkitViaTask(getServer().getScheduler().runTaskTimerAsynchronously(this, runnable, 0, ticks));
    }

    @Override
    public PlatformTask runSync(Runnable runnable) {
        return new BukkitViaTask(getServer().getScheduler().runTask(this, runnable));
    }

    @Override
    public PlatformTask runSync(Runnable runnable, long delay) {
        return new BukkitViaTask(getServer().getScheduler().runTaskLater(this, runnable, delay));
    }

    public PlatformTask<?> runSyncAt(Runnable runnable, Block block) {
        return runSync(runnable);
    }

    public PlatformTask<?> runSyncFor(Runnable runnable, Player player) {
        return runSync(() -> {
            if (player.isOnline()) {
                runnable.run();
            }
        });
    }

    @Override
    public PlatformTask runRepeatingSync(Runnable runnable, long period) {
        return new BukkitViaTask(getServer().getScheduler().runTaskTimer(this, runnable, 0, period));
    }

    @Override
    public void sendMessage(UserConnection connection, String message) {
        UUID uuid = connection.getProtocolInfo().getUuid();
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            player.sendMessage(message);
        }
    }

    @Override
    public boolean kickPlayer(UserConnection connection, String message) {
        UUID uuid = connection.getProtocolInfo().getUuid();
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            player.kickPlayer(message);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onReload() {
        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
            getLogger().severe("ViaVersion is already loaded, we're going to kick all the players... because otherwise we'll crash because of ProtocolLib.");
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer(ChatColor.translateAlternateColorCodes('&', conf.getReloadDisconnectMsg()));
            }

        } else {
            getLogger().severe("ViaVersion is already loaded, this should work fine. If you get any console errors, try rebooting.");
        }
    }

    @Override
    public JsonObject getDump() {
        JsonObject platformSpecific = new JsonObject();

        List<PluginInfo> plugins = new ArrayList<>();
        for (Plugin p : Bukkit.getPluginManager().getPlugins())
            plugins.add(new PluginInfo(p.isEnabled(), p.getDescription().getName(), p.getDescription().getVersion(), p.getDescription().getMain(), p.getDescription().getAuthors()));

        platformSpecific.add("plugins", GsonUtil.getGson().toJsonTree(plugins));

        return platformSpecific;
    }

    @Override
    public BukkitViaConfig getConf() {
        return conf;
    }

    @Override
    public ViaAPI<Player> getApi() {
        return api;
    }

    @Override
    public final Collection<UnsupportedSoftware> getUnsupportedSoftwareClasses() {
        final List<UnsupportedSoftware> list = new ArrayList<>(ViaPlatform.super.getUnsupportedSoftwareClasses());
        list.add(new UnsupportedPlugin.Builder().name("software to mess with message signing").reason(UnsupportedPlugin.Reason.SECURE_CHAT_BYPASS)
                .addPlugin("NoEncryption").addPlugin("NoReport")
                .addPlugin("NoChatReports").addPlugin("NoChatReport").build());
        return Collections.unmodifiableList(list);
    }

    @Override
    public boolean hasPlugin(final String name) {
        return getServer().getPluginManager().getPlugin(name) != null;
    }

    @Override
    public boolean couldBeReloading() {
        return false;
    }

    public boolean isLateBind() {
        return lateBind;
    }

    public static boolean isProtocolSupport() {
        return protocolSupport;
    }

    /**
     * @deprecated use {@link Via#getAPI()} instead
     */
    @Deprecated
    public static ViaVersionPlugin getInstance() {
        return instance;
    }
}