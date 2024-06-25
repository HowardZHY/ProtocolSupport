package protocolsupport.protocol.transformer.v_legacy;

import io.netty.channel.Channel;
import protocolsupport.api.Connection;
import protocolsupport.protocol.core.ChannelHandlers;
import protocolsupport.protocol.core.IPipeLineBuilder;

public class PipeLineBuilder implements IPipeLineBuilder {

	private static final LegacyLoginAndPingHandler legacyHandler = new LegacyLoginAndPingHandler();

	@Override
	public void buildPipeLine(Channel channel, Connection connection) {
		channel.pipeline().addAfter(ChannelHandlers.TIMEOUT, ChannelHandlers.LEGACY_KICK, legacyHandler);
	}

}
