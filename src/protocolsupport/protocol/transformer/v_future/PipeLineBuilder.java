package protocolsupport.protocol.transformer.v_future;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import net.minecraft.server.v1_8_R3.NetworkManager;
import protocolsupport.api.Connection;
import protocolsupport.protocol.core.ChannelHandlers;
import protocolsupport.protocol.core.IPipeLineBuilder;
import protocolsupport.protocol.pipeline.common.VarIntFrameDecoder;
import protocolsupport.protocol.pipeline.common.VarIntFrameEncoder;
import protocolsupport.protocol.transformer.v_1_8.HandshakeListener;

public class PipeLineBuilder implements IPipeLineBuilder {

    @Override
    public void buildPipeLine(Channel channel, Connection connection) {
        connection.setFraming(channel.pipeline(), new VarIntFrameDecoder(), new VarIntFrameEncoder());
    }
    @Override
    public void buildCodec(Channel channel, Connection connection) {
        ChannelPipeline pipeline = channel.pipeline();
        NetworkManager networkmanager =  (NetworkManager) pipeline.get(ChannelHandlers.NETWORK_MANAGER);
        networkmanager.a(new HandshakeListener(networkmanager));
    }

}