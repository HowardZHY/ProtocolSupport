package protocolsupport;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import protocolsupport.commands.CommandHandler;
import protocolsupport.injector.ServerInjector;
import protocolsupport.injector.network.NettyInjector;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.core.initial.InitialPacketDecoder;
import protocolsupport.protocol.transformer.handlers.AbstractLoginListener;
import protocolsupport.server.listeners.PlayerListener;
import protocolsupport.utils.netty.Allocator;
import protocolsupport.utils.netty.Compressor;

public class ProtocolSupport extends JavaPlugin {

	@Override
	public void onLoad() {}

	@Override
	public void onEnable() {
		Listener listener = new Listener() {
			@EventHandler
			public void onLogin(PlayerLoginEvent event) {
				event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Server is still loading!");
			}
		};
		Bukkit.getPluginManager().registerEvents(listener, this);
		Bukkit.getScheduler().runTask(this, () -> {
			try {
				Allocator.init();
				Compressor.init();
				ServerBoundPacket.init();
				ClientBoundPacket.init();
				InitialPacketDecoder.init();
				AbstractLoginListener.init();
				NettyInjector.inject();
				ServerInjector.inject();
			} catch (Throwable t) {
				t.printStackTrace();
				Bukkit.shutdown();
			}
			getCommand("protocolsupport").setExecutor(new CommandHandler());
			getServer().getPluginManager().registerEvents(new PlayerListener(), this);
			HandlerList.unregisterAll(listener);
		});
	}

	@Override
	public void onDisable() {
		Bukkit.shutdown();
	}

	public static void logWarning(String message) {
		JavaPlugin.getPlugin(ProtocolSupport.class).getLogger().warning(message);
	}

	public static void logInfo(String message) {
		JavaPlugin.getPlugin(ProtocolSupport.class).getLogger().info(message);
	}

}
