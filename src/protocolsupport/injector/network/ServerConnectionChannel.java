package protocolsupport.injector.network;

import io.netty.channel.*;
import net.minecraft.server.v1_8_R3.EnumProtocolDirection;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.core.ChannelHandlers;
import protocolsupport.protocol.core.FakePacketListener;
import protocolsupport.protocol.core.initial.InitialPacketDecoder;
import protocolsupport.protocol.core.timeout.SimpleReadTimeoutHandler;
import protocolsupport.protocol.core.wrapped.WrappedPrepender;
import protocolsupport.protocol.core.wrapped.WrappedSplitter;
import protocolsupport.protocol.pipeline.common.LogicHandler;
import protocolsupport.protocol.pipeline.common.RawPacketDataCaptureReceive;
import protocolsupport.protocol.pipeline.common.RawPacketDataCaptureSend;
import protocolsupport.protocol.storage.ProtocolStorage;

public class ServerConnectionChannel extends ChannelInitializer<Channel> {

	public ServerConnectionChannel() {}

	private static final int IPTOS_THROUGHPUT = 0x08;
	private static final int IPTOS_LOWDELAY = 0x10;

	@Override
	protected void initChannel(Channel channel) {
		try {
			channel.config().setOption(ChannelOption.IP_TOS, IPTOS_THROUGHPUT | IPTOS_LOWDELAY);
		} catch (ChannelException channelexception) {
			if (MinecraftServer.getServer().isDebugging()) {
				System.err.println("Unable to set IP_TOS option: " + channelexception.getMessage());
			}
		}
		try {
			channel.config().setOption(ChannelOption.TCP_NODELAY, true);
		} catch (ChannelException channelexception) {
			if (MinecraftServer.getServer().isDebugging()) {
				System.err.println("Unable to set TCP_NODELAY option: " + channelexception.getMessage());
			}
		}
		NetworkManager networkmanager = new NetworkManager(EnumProtocolDirection.SERVERBOUND);
		networkmanager.a(new FakePacketListener());
		ConnectionImpl connection = new ConnectionImpl(networkmanager, ProtocolVersion.UNKNOWN);
		connection.storeInChannel(channel);
		ProtocolStorage.setConnection(channel.remoteAddress(), connection);
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addAfter(ChannelHandlers.TIMEOUT, ChannelHandlers.INITIAL_DECODER, new InitialPacketDecoder());
		pipeline.addBefore(ChannelHandlers.NETWORK_MANAGER, ChannelHandlers.LOGIC, new LogicHandler(connection));
		pipeline.remove("legacy_query");
		pipeline.replace(ChannelHandlers.TIMEOUT, ChannelHandlers.TIMEOUT, new SimpleReadTimeoutHandler(30));
		pipeline.replace(ChannelHandlers.SPLITTER, ChannelHandlers.SPLITTER, new WrappedSplitter());
		pipeline.replace(ChannelHandlers.PREPENDER, ChannelHandlers.PREPENDER, new WrappedPrepender());
		pipeline.addAfter(ChannelHandlers.PREPENDER, ChannelHandlers.RAW_CAPTURE_SEND, new RawPacketDataCaptureSend(connection));
		pipeline.addAfter(ChannelHandlers.SPLITTER, ChannelHandlers.RAW_CAPTURE_RECEIVE, new RawPacketDataCaptureReceive(connection));
		//channel.pipeline().addLast(ChannelHandlers.NETWORK_MANAGER, networkmanager);
	}

}
