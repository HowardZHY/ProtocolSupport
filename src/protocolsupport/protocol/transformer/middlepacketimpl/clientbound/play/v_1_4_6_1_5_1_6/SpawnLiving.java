package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_6_1_5_1_6;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleSpawnLiving;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.protocol.typeremapper.watchedentity.WatchedDataRemapper;
import protocolsupport.utils.DataWatcherSerializer;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class SpawnLiving extends MiddleSpawnLiving<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
		if (type == 30) { //skip armor stand
			return RecyclableEmptyList.get();
		}
		if ((version.isAprilFools2_0(version)) && type == 100){
			return RecyclableEmptyList.get(); //skip horse fix crash - 2.0
		}
		if (version.isBeforeOrEq(ProtocolVersion.MINECRAFT_1_4_5)) {
			if (type == 22) {
				return RecyclableEmptyList.get(); //skip firework rocket - 1.4.5
			}
		}
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_SPAWN_LIVING_ID, version);
		serializer.writeInt(entityId);
		serializer.writeByte(IdRemapper.ENTITY.getTable(version).getRemap(type));
		serializer.writeInt(x);
		serializer.writeInt(y);
		serializer.writeInt(z);
		serializer.writeByte(yaw);
		serializer.writeByte(pitch);
		serializer.writeByte(headPitch);
		serializer.writeShort(motX);
		serializer.writeShort(motY);
		serializer.writeShort(motZ);
		serializer.writeBytes(DataWatcherSerializer.encodeData(version, WatchedDataRemapper.transform(wentity, metadata, version)));
		return RecyclableSingletonList.create(serializer);
	}

}
