package protocolsupport.protocol.pipeline.common;
import java.util.List;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import protocolsupport.protocol.core.IPacketSplitter;

public class FakeFrameDecoder implements IPacketSplitter {

    @Override
    public void split(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) {

    }
}