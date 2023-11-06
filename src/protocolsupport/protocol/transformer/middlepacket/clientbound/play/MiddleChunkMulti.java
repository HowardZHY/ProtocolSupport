package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;
import protocolsupport.protocol.transformer.utils.ChunkTransformer;
import protocolsupport.utils.netty.ChannelUtils;

public abstract class MiddleChunkMulti<T> extends ClientBoundMiddlePacket<T> {

	protected boolean hasSkyLight;

	protected int[] chunkX;

	protected int[] chunkZ;

	protected int[] bitmap;

	protected byte[][] data;

	public boolean BEFORE_OR_1_4_5;

	@Override
	public void readFromServerData(PacketDataSerializer serializer) throws IOException {

		BEFORE_OR_1_4_5 = serializer.getVersion().isBeforeOrEq(ProtocolVersion.MINECRAFT_1_4_5);

		hasSkyLight = serializer.readBoolean();
		//TODO: Fix 1.4.5 and below?

		int count = serializer.readVarInt();
		chunkX = new int[count];
		chunkZ = new int[count];
		bitmap = new int[count];
		data = new byte[count][];
		for (int i = 0; i < count; i++) {
			chunkX[i] = serializer.readInt();
			chunkZ[i] = serializer.readInt();
			bitmap[i] = serializer.readUnsignedShort();
		}
		for (int i = 0; i < count; i++) {
			System.out.println(BEFORE_OR_1_4_5);
			if (BEFORE_OR_1_4_5) {
				data[i] = ChannelUtils.toArray(serializer.readBytes(ChunkTransformer.calcDataSize145(Integer.bitCount(bitmap[i]), hasSkyLight, true)));
			} else {
				data[i] = ChannelUtils.toArray(serializer.readBytes(ChunkTransformer.calcDataSize(Integer.bitCount(bitmap[i]), hasSkyLight, true)));
			}
		}
	}
}
