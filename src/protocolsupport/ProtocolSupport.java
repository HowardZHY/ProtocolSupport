package protocolsupport;

import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import protocolsupport.api.Connection;
import protocolsupport.api.events.ConnectionOpenEvent;
import protocolsupport.commands.CommandHandler;
import protocolsupport.compat.CompatListener;
import protocolsupport.injector.ServerInjector;
import protocolsupport.injector.network.NettyInjector;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.core.initial.InitialPacketDecoder;
import protocolsupport.protocol.transformer.handlers.AbstractLoginListener;
import protocolsupport.server.listeners.PlayerListener;
import protocolsupport.utils.netty.Allocator;
import protocolsupport.utils.netty.Compressor;

public class ProtocolSupport extends JavaPlugin {

	public static boolean VIA = true;

	@Override
	public void onLoad() {
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
	}

	@Override
	public void onEnable() {
		VIA = Bukkit.getPluginManager().getPlugin("ViaVersion") != null;
		if (VIA) {
			registerPSConnectListener(this);
		}
		getCommand("protocolsupport").setExecutor(new CommandHandler());
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}

	@Override
	public void onDisable() {
		Bukkit.shutdown();
	}

	public static void registerPSConnectListener(Plugin plugin) {
		logInfo("Registering Via compat connection listener");
		try {
			plugin.getServer().getPluginManager().registerEvent(ConnectionOpenEvent.class, new Listener() {}, EventPriority.HIGH, (listener, event) -> {
				try {
					ConnectionOpenEvent coe = (ConnectionOpenEvent) event;
					Connection connection = coe.getConnection();
					CompatListener compatlistener = new CompatListener((ConnectionImpl) connection);
					connection.addPacketListener(compatlistener);
				} catch (Exception e) {
					logWarning("Error when handling ProtocolSupport event" + e);
				}
			}, plugin);
		} catch (Exception e) {
			logWarning("Unable to register listener" + e);
		}
	}

	public static void logWarning(String message) {
		JavaPlugin.getPlugin(ProtocolSupport.class).getLogger().warning(message);
	}

	public static void logInfo(String message) {
		JavaPlugin.getPlugin(ProtocolSupport.class).getLogger().info(message);
	}

}
