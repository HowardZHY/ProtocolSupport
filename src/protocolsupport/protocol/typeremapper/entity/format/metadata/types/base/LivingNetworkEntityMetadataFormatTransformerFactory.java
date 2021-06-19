package protocolsupport.protocol.typeremapper.entity.format.metadata.types.base;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeremapper.entity.format.metadata.object.value.NetworkEntityMetadataObjectIndexValueBooleanToByteTransformer;
import protocolsupport.protocol.typeremapper.entity.format.metadata.object.value.NetworkEntityMetadataObjectIndexValueNoOpTransformer;
import protocolsupport.protocol.typeremapper.entity.format.metadata.object.value.NetworkEntityMetadataObjectIndexValueNumberToByteTransformer;
import protocolsupport.protocol.typeremapper.entity.format.metadata.object.value.NetworkEntityMetadataObjectIndexValueNumberToIntTransformer;
import protocolsupport.protocol.typeremapper.entity.format.metadata.object.value.NetworkEntityMetadataObjectIndexValueOptionalChatToLegacyTextTransformer;
import protocolsupport.protocol.types.networkentity.metadata.NetworkEntityMetadataObjectIndex;
import protocolsupport.protocol.utils.ProtocolVersionsHelper;

public class LivingNetworkEntityMetadataFormatTransformerFactory extends BaseNetworkEntityMetadataFormatTransformerFactory {

	public static final LivingNetworkEntityMetadataFormatTransformerFactory INSTANCE = new LivingNetworkEntityMetadataFormatTransformerFactory();

	protected LivingNetworkEntityMetadataFormatTransformerFactory() {
		add(new NetworkEntityMetadataObjectIndexValueOptionalChatToLegacyTextTransformer(NetworkEntityMetadataObjectIndex.Entity.NAMETAG, 10), ProtocolVersionsHelper.ALL_1_7);
		add(new NetworkEntityMetadataObjectIndexValueOptionalChatToLegacyTextTransformer(NetworkEntityMetadataObjectIndex.Entity.NAMETAG, 10, 64), ProtocolVersionsHelper.ALL_1_6);
		add(new NetworkEntityMetadataObjectIndexValueOptionalChatToLegacyTextTransformer(NetworkEntityMetadataObjectIndex.Entity.NAMETAG, 5, 64), ProtocolVersionsHelper.DOWN_1_5_2);

		add(new NetworkEntityMetadataObjectIndexValueBooleanToByteTransformer(NetworkEntityMetadataObjectIndex.Entity.NAMETAG_VISIBLE, 11), ProtocolVersionsHelper.RANGE__1_6__1_7);
		add(new NetworkEntityMetadataObjectIndexValueBooleanToByteTransformer(NetworkEntityMetadataObjectIndex.Entity.NAMETAG_VISIBLE, 6), ProtocolVersionsHelper.DOWN_1_5_2);

		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.HAND_USE, 8), ProtocolVersionsHelper.UP_1_17);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.HAND_USE, 7), ProtocolVersionsHelper.RANGE__1_14__1_16_4);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.HAND_USE, 6), ProtocolVersionsHelper.RANGE__1_10__1_13_2);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.HAND_USE, 5), ProtocolVersionsHelper.ALL_1_9);

		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.HEALTH, 9), ProtocolVersionsHelper.UP_1_17);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.HEALTH, 8), ProtocolVersionsHelper.RANGE__1_14__1_16_4);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.HEALTH, 7), ProtocolVersionsHelper.RANGE__1_10__1_13_2);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.HEALTH, 6), ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_9_4, ProtocolVersion.MINECRAFT_1_6_1));

		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_COLOR, 10), ProtocolVersionsHelper.UP_1_17);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_COLOR, 9), ProtocolVersionsHelper.RANGE__1_14__1_16_4);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_COLOR, 8), ProtocolVersionsHelper.RANGE__1_10__1_13_2);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_COLOR, 7), ProtocolVersionsHelper.ALL_1_9);
		add(new NetworkEntityMetadataObjectIndexValueNumberToIntTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_COLOR, 7), ProtocolVersionsHelper.RANGE__1_6__1_8);
		add(new NetworkEntityMetadataObjectIndexValueNumberToIntTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_COLOR, 8), ProtocolVersionsHelper.DOWN_1_5_2);

		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_AMBIENT, 11), ProtocolVersionsHelper.UP_1_17);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_AMBIENT, 10), ProtocolVersionsHelper.RANGE__1_14__1_16_4);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_AMBIENT, 9), ProtocolVersionsHelper.RANGE__1_10__1_13_2);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_AMBIENT, 8), ProtocolVersionsHelper.ALL_1_9);
		add(new NetworkEntityMetadataObjectIndexValueBooleanToByteTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_AMBIENT, 8), ProtocolVersionsHelper.RANGE__1_6__1_8);
		add(new NetworkEntityMetadataObjectIndexValueBooleanToByteTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.POTION_AMBIENT, 9), ProtocolVersionsHelper.DOWN_1_5_2);

		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.ARROWS_IN, 12), ProtocolVersionsHelper.UP_1_17);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.ARROWS_IN, 11), ProtocolVersionsHelper.RANGE__1_14__1_16_4);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.ARROWS_IN, 10), ProtocolVersionsHelper.RANGE__1_10__1_13_2);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.ARROWS_IN, 9), ProtocolVersionsHelper.ALL_1_9);
		add(new NetworkEntityMetadataObjectIndexValueNumberToByteTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.ARROWS_IN, 9), ProtocolVersionsHelper.RANGE__1_6__1_8);
		add(new NetworkEntityMetadataObjectIndexValueNumberToByteTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.ARROWS_IN, 10), ProtocolVersionsHelper.DOWN_1_5_2);

		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.ABSORBTION_HEALTH, 13), ProtocolVersionsHelper.UP_1_17);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.ABSORBTION_HEALTH, 12), ProtocolVersionsHelper.RANGE__1_15__1_16_4);

		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.BED_LOCATION, 14), ProtocolVersionsHelper.UP_1_17);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.BED_LOCATION, 13), ProtocolVersionsHelper.RANGE__1_15__1_16_4);
		add(new NetworkEntityMetadataObjectIndexValueNoOpTransformer(NetworkEntityMetadataObjectIndex.EntityLiving.BED_LOCATION, 12), ProtocolVersionsHelper.ALL_1_14);
	}

}