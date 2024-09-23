package protocolsupport.protocol.core.initial;

import gnu.trove.map.hash.TIntObjectHashMap;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.utils.netty.ChannelUtils;

import java.util.Arrays;

public class ProtocolUtils {

	@SuppressWarnings("all")
	protected static ProtocolVersion get16PingVersion(int protocolId) {
		switch (protocolId) {
			case 78: {
				return ProtocolVersion.MINECRAFT_1_6_4;
			}
			case 77: {
				return ProtocolVersion.MINECRAFT_1_6_3;
			}
			case 74: {
				return ProtocolVersion.MINECRAFT_1_6_2;
			}
			case 73: {
				return ProtocolVersion.MINECRAFT_1_6_1;
			}
			case 72: {
				return ProtocolVersion.MINECRAFT_1_6;
			}
			default: {
				return ProtocolVersion.MINECRAFT_1_6_4;
			}
		}
	}

	protected static ProtocolVersion readOldHandshake(ByteBuf data) {
		ProtocolVersion version = ProtocolVersion.fromId(0, data.readUnsignedByte());
		return version != ProtocolVersion.UNKNOWN ? version : ProtocolVersion.MINECRAFT_LEGACY;
	}

	protected static ProtocolVersion readNettyHandshake(ByteBuf data) {
		int packetId = ChannelUtils.readVarInt(data);
		if (packetId == 0x00) {
			ProtocolVersion version = ProtocolVersion.fromId(1, ChannelUtils.readVarInt(data));
			return version != ProtocolVersion.UNKNOWN ? version : ProtocolVersion.MINECRAFT_FUTURE;
		} else {
			throw new DecoderException(packetId + "is not a valid packet id");
		}
	}

	private static final TIntObjectHashMap<ProtocolVersion> byOldProtocolId = new TIntObjectHashMap<>();

	private static final TIntObjectHashMap<ProtocolVersion> byNewProtocolId = new TIntObjectHashMap<>();

	static {
		Arrays.stream(ProtocolVersion.getAllBefore(ProtocolVersion.MINECRAFT_1_6_4)).forEach(version -> byOldProtocolId.put(version.getId(), version));
		Arrays.stream(ProtocolVersion.getAllAfter(ProtocolVersion.MINECRAFT_1_7_5)).forEach(version -> byNewProtocolId.put(version.getId(), version));
	}

	public static ProtocolVersion getOldProtocolVersion(int pid) {
		ProtocolVersion version = byOldProtocolId.get(pid);
		return version != null ? version : ProtocolVersion.MINECRAFT_LEGACY;
	}

	public static ProtocolVersion getNewProtocolVersion(int pid) {
		ProtocolVersion version = byNewProtocolId.get(pid);
		return version != null ? version : ProtocolVersion.MINECRAFT_FUTURE;
	}

}
