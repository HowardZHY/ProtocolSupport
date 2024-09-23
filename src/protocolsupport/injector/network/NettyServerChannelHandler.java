package protocolsupport.injector.network;

import io.netty.channel.*;

public class NettyServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
        Channel channel = (Channel) o;
        channel.pipeline().addLast(new ServerConnectionChannel());
        super.channelRead(ctx, o);
    }

}