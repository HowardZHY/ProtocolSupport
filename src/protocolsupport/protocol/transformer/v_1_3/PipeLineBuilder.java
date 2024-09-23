package protocolsupport.protocol.transformer.v_1_3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import net.minecraft.server.v1_8_R3.NetworkManager;
import protocolsupport.api.Connection;
import protocolsupport.protocol.core.ChannelHandlers;
import protocolsupport.protocol.core.IPipeLineBuilder;
import protocolsupport.protocol.pipeline.common.VarIntFrameDecoder;
import protocolsupport.protocol.pipeline.common.VarIntFrameEncoder;

public class PipeLineBuilder implements IPipeLineBuilder {

	@Override
	public void buildPipeLine(Channel channel, Connection connection) {
		connection.setFraming(channel.pipeline(), new VarIntFrameDecoder(), new VarIntFrameEncoder());
	}

	@Override
	public void buildCodec(Channel channel, Connection connection) {
		ChannelPipeline pipeline = channel.pipeline();
		NetworkManager networkmanager = (NetworkManager) pipeline.get(ChannelHandlers.NETWORK_MANAGER);
		networkmanager.a(new HandshakeListener(networkmanager));
		ChannelHandlers.getDecoder(pipeline).setRealDecoder(new PacketDecoder());
		ChannelHandlers.getEncoder(pipeline).setRealEncoder(new PacketEncoder(connection.getVersion()));
	}

}