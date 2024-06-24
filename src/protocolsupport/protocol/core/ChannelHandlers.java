package protocolsupport.protocol.core;

import io.netty.channel.ChannelPipeline;
import protocolsupport.protocol.core.wrapped.WrappedDecoder;
import protocolsupport.protocol.core.wrapped.WrappedEncoder;
import protocolsupport.protocol.core.wrapped.WrappedPrepender;
import protocolsupport.protocol.core.wrapped.WrappedSplitter;

public class ChannelHandlers {

	public static final String TIMEOUT = "timeout";
	public static final String SPLITTER = "splitter";
	public static final String PREPENDER = "prepender";
	public static final String DECODER = "decoder";
	public static final String ENCODER = "encoder";
	public static final String NETWORK_MANAGER = "packet_handler";
	public static final String DECRYPT = "decrypt";
	public static final String INITIAL_DECODER = "ps_initial_decoder";
	public static final String LEGACY_KICK = "ps_legacy_kick";
	public static final String DECODER_TRANSFORMER = "ps_decoder_transformer";
	public static final String ENCODER_TRANSFORMER = "ps_ncoder_transformer";
	public static final String LOGIC = "ps_logic";

	public static WrappedDecoder getDecoder(ChannelPipeline pipeline) {
		return (WrappedDecoder) pipeline.get(DECODER);
	}

	public static WrappedEncoder getEncoder(ChannelPipeline pipeline) {
		return (WrappedEncoder) pipeline.get(ENCODER);
	}

	public static WrappedSplitter getSplitter(ChannelPipeline pipeline) {
		return (WrappedSplitter) pipeline.get(SPLITTER);
	}

	public static WrappedPrepender getPrepender(ChannelPipeline pipeline) {
		return (WrappedPrepender) pipeline.get(PREPENDER);
	}

}
