package protocolsupport.protocol.pipeline.version.v_1_17.r1;

import protocolsupport.api.utils.NetworkState;
import protocolsupport.protocol.packet.middle.impl.serverbound.IServerboundMiddlePacketV17r1;
import protocolsupport.protocol.packet.middle.impl.serverbound.handshake.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.SetProtocol;
import protocolsupport.protocol.packet.middle.impl.serverbound.login.v_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.LoginCustomPayload;
import protocolsupport.protocol.packet.middle.impl.serverbound.login.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.LoginStart;
import protocolsupport.protocol.packet.middle.impl.serverbound.login.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EncryptionResponse;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.ResourcePackStatus;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.AdvancementTab;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_13_14r1_14r2_15_16r1_16r2_17r1.EditBook;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.CraftRecipeRequest;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.CustomPayload;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.NameItem;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.PickItem;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.QueryEntityNBT;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.SelectTrade;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.SetBeaconEffect;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.TabComplete;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.UpdateCommandMinecart;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_14r1_14r2_15_16r1_16r2_17r1_17r2_18.BlockDig;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_14r1_14r2_15_16r1_16r2_17r1_17r2_18.BlockPlace;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_14r1_14r2_15_16r1_16r2_17r1_17r2_18.DifficultyChange;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_14r1_14r2_15_16r1_16r2_17r1_17r2_18.DifficultyLock;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_14r1_14r2_15_16r1_16r2_17r1_17r2_18.QueryBlockNBT;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_14r1_14r2_15_16r1_16r2_17r1_17r2_18.UpdateCommandBlock;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_14r1_14r2_15_16r1_16r2_17r1_17r2_18.UpdateSign;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_14r1_14r2_15_16r1_16r2_17r1_17r2_18.UpdateStructureBlock;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_16r1_16r2_17r1_17r2_18.JigsawGenerate;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_16r1_16r2_17r1_17r2_18.JigsawUpdate;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_16r1_16r2_17r1_17r2_18.PlayerAbilities;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_16r1_16r2_17r1_17r2_18.UseEntity;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_16r2_17r1_17r2_18.RecipeBookRecipe;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_16r2_17r1_17r2_18.RecipeBookState;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_17r1.InventoryClick;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_17r1_17r2.ClientSettings;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_17r1_17r2_18.InventoryClose;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_17r1_17r2_18.SyncPong;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.CreativeSetSlot;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.Ground;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.HeldSlot;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.InventoryButton;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.Look;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.Chat;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.ClientCommand;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityAction;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.KeepAlive;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.Move;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.Spectate;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.SteerVehicle;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.Animation;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.MoveLook;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.MoveVehicle;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.SteerBoat;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.TeleportAccept;
import protocolsupport.protocol.packet.middle.impl.serverbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.UseItem;
import protocolsupport.protocol.packet.middle.impl.serverbound.status.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.Ping;
import protocolsupport.protocol.packet.middle.impl.serverbound.status.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.ServerInfoRequest;
import protocolsupport.protocol.pipeline.IPacketDataChannelIO;
import protocolsupport.protocol.pipeline.version.util.decoder.AbstractModernPacketDecoder;
import protocolsupport.protocol.storage.netcache.NetworkDataCache;

public class PacketDecoder extends AbstractModernPacketDecoder<IServerboundMiddlePacketV17r1> {

	public PacketDecoder(IPacketDataChannelIO io, NetworkDataCache cache) {
		super(io, cache);
		registry.register(NetworkState.HANDSHAKING, 0x00, SetProtocol::new);
		registry.register(NetworkState.LOGIN, 0x00, LoginStart::new);
		registry.register(NetworkState.LOGIN, 0x01, EncryptionResponse::new);
		registry.register(NetworkState.LOGIN, 0x02, LoginCustomPayload::new);
		registry.register(NetworkState.STATUS, 0x00, ServerInfoRequest::new);
		registry.register(NetworkState.STATUS, 0x01, Ping::new);
		registry.register(NetworkState.PLAY, 0x00, TeleportAccept::new);
		registry.register(NetworkState.PLAY, 0x01, QueryBlockNBT::new);
		registry.register(NetworkState.PLAY, 0x02, DifficultyChange::new);
		registry.register(NetworkState.PLAY, 0x03, Chat::new);
		registry.register(NetworkState.PLAY, 0x04, ClientCommand::new);
		registry.register(NetworkState.PLAY, 0x05, ClientSettings::new);
		registry.register(NetworkState.PLAY, 0x06, TabComplete::new);
		registry.register(NetworkState.PLAY, 0x07, InventoryButton::new);
		registry.register(NetworkState.PLAY, 0x08, InventoryClick::new);
		registry.register(NetworkState.PLAY, 0x09, InventoryClose::new);
		registry.register(NetworkState.PLAY, 0x0A, CustomPayload::new);
		registry.register(NetworkState.PLAY, 0x0B, EditBook::new);
		registry.register(NetworkState.PLAY, 0x0C, QueryEntityNBT::new);
		registry.register(NetworkState.PLAY, 0x0D, UseEntity::new);
		registry.register(NetworkState.PLAY, 0x0E, JigsawGenerate::new);
		registry.register(NetworkState.PLAY, 0x0F, KeepAlive::new);
		registry.register(NetworkState.PLAY, 0x10, DifficultyLock::new);
		registry.register(NetworkState.PLAY, 0x11, Move::new);
		registry.register(NetworkState.PLAY, 0x12, MoveLook::new);
		registry.register(NetworkState.PLAY, 0x13, Look::new);
		registry.register(NetworkState.PLAY, 0x14, Ground::new);
		registry.register(NetworkState.PLAY, 0x15, MoveVehicle::new);
		registry.register(NetworkState.PLAY, 0x16, SteerBoat::new);
		registry.register(NetworkState.PLAY, 0x17, PickItem::new);
		registry.register(NetworkState.PLAY, 0x18, CraftRecipeRequest::new);
		registry.register(NetworkState.PLAY, 0x19, PlayerAbilities::new);
		registry.register(NetworkState.PLAY, 0x1A, BlockDig::new);
		registry.register(NetworkState.PLAY, 0x1B, EntityAction::new);
		registry.register(NetworkState.PLAY, 0x1C, SteerVehicle::new);
		registry.register(NetworkState.PLAY, 0x1D, SyncPong::new);
		registry.register(NetworkState.PLAY, 0x1E, RecipeBookState::new);
		registry.register(NetworkState.PLAY, 0x1F, RecipeBookRecipe::new);
		registry.register(NetworkState.PLAY, 0x20, NameItem::new);
		registry.register(NetworkState.PLAY, 0x21, ResourcePackStatus::new);
		registry.register(NetworkState.PLAY, 0x22, AdvancementTab::new);
		registry.register(NetworkState.PLAY, 0x23, SelectTrade::new);
		registry.register(NetworkState.PLAY, 0x24, SetBeaconEffect::new);
		registry.register(NetworkState.PLAY, 0x25, HeldSlot::new);
		registry.register(NetworkState.PLAY, 0x26, UpdateCommandBlock::new);
		registry.register(NetworkState.PLAY, 0x27, UpdateCommandMinecart::new);
		registry.register(NetworkState.PLAY, 0x28, CreativeSetSlot::new);
		registry.register(NetworkState.PLAY, 0x29, JigsawUpdate::new);
		registry.register(NetworkState.PLAY, 0x2A, UpdateStructureBlock::new);
		registry.register(NetworkState.PLAY, 0x2B, UpdateSign::new);
		registry.register(NetworkState.PLAY, 0x2C, Animation::new);
		registry.register(NetworkState.PLAY, 0x2D, Spectate::new);
		registry.register(NetworkState.PLAY, 0x2E, BlockPlace::new);
		registry.register(NetworkState.PLAY, 0x2F, UseItem::new);
	}

}
