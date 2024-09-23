package protocolsupport.injector.network;

import io.netty.channel.*;

import java.util.Map.Entry;

public class NettyServerChannelHandler extends ChannelInboundHandlerAdapter {

    private static final ServerConnectionChannelPlacer serverConnectionChannelPlacer = new ServerConnectionChannelPlacer();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
        Channel channel = (Channel) o;
        channel.pipeline().addFirst(serverConnectionChannelPlacer);
        super.channelRead(ctx, o);
    }

    private static class ServerConnectionChannelPlacer extends ChannelInitializer<Channel> {
        private static final ServerConnectionChannel serverConnectionChannel = new ServerConnectionChannel();

        @Override
        protected void initChannel(Channel channel) throws Exception {
            String nativeServerConnection = findNativeServerConnectionChannelHandlerName(channel.pipeline());
            if (nativeServerConnection != null) {
                channel.pipeline().addAfter(nativeServerConnection, "ps_server_connection", serverConnectionChannel);
            } else {
                channel.pipeline().addLast(serverConnectionChannel);
            }
        }

        private static String findNativeServerConnectionChannelHandlerName(ChannelPipeline pipeline) {
            for (Entry<String, ChannelHandler> entry : pipeline) {
                if (entry.getValue().getClass().getName().equals("net.minecraft.server.v1_8_R3.ServerConnection$4")) {
                    return entry.getKey();
                }
            }
            return null;
        }
    }

}