package protocolsupport.protocol.pipeline.version.v_1_11;

import protocolsupport.api.utils.NetworkState;
import protocolsupport.protocol.packet.ClientBoundPacketType;
import protocolsupport.protocol.packet.middle.impl.clientbound.IClientboundMiddlePacketV11;
import protocolsupport.protocol.packet.middle.impl.clientbound.login.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15.LoginSuccess;
import protocolsupport.protocol.packet.middle.impl.clientbound.login.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.LoginDisconnect;
import protocolsupport.protocol.packet.middle.impl.clientbound.login.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EncryptionRequest;
import protocolsupport.protocol.packet.middle.impl.clientbound.login.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.SetCompression;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopAdvancements;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopAdvanementsTab;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopCraftRecipeConfirm;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopDeclareCommands;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopDeclareRecipes;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopDeclareTags;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopLookAt;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopSetViewCenter;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopStatistics;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopStopSound;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopUnlockRecipes;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopUpdateSimulationDistance;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopUpdateViewDistance;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.noop.NoopVibration;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_10_11_12r1_12r2_13.EntitySound;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_10_11_12r1_12r2_13.Explosion;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityEffectAdd;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.WorldCustomSound;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.WorldSound;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_11_12r1_12r2.SpawnLiving;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.Actionbar;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.TitleAnimation;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.TitleClear;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.TitleSubText;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.TitleText;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.CollectEffect;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.InventoryClose;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.InventorySetItems;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.InventorySetSlot;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.SyncPing;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.InventoryData;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.ScoreboardDisplay;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.TimeUpdate;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.PlayerAbilities;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2.TabComplete;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2_13.ChangeDimension;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityAnimation;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.GameStateChange;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.HeldSlot;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.KickDisconnect;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.BlockAction;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.BlockBreakConfirm;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.BlockChangeMulti;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.BlockChangeSingle;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.CustomPayload;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.InventoryHorseOpen;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.InventoryOpen;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.MerchantTradeList;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.ScoreboardObjective;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2.WorldParticle;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13.BlockBreakAnimation;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13.BlockOpenSignEditor;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13.BlockTileUpdate;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13.ServerDifficulty;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13.SpawnPosition;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13.StartGame;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13.WorldEvent;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15.Chat;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15.EntityAttributes;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.CombatBegin;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.CombatDeath;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.CombatEnd;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.ResourcePack;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.WorldBorderCenter;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.WorldBorderInit;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.WorldBorderLerpSize;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.WorldBorderSize;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.WorldBorderWarnDelay;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.WorldBorderWarnDistance;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.Camera;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityEffectRemove;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityHeadRotation;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityLook;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.KeepAlive;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.PlayerListHeaderFooter;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.PlayerListSetEntry;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.ScoreboardScore;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.SetExperience;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.SetHealth;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2.BookOpen;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2.ScoreboardTeam;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2.SpawnPainting;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2.UpdateMap;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13.ChunkUnload;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13.EntityMetadata;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13.EntityStatus;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13.SpawnObject;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2.SpawnNamed;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15.EntityEquipment;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.EntityDestroy;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2.SetPosition;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.BossBar;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityLeash;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityPassengers;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityRelMove;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityRelMoveLook;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityTeleport;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.EntityVelocity;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.SetCooldown;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.SpawnExpOrb;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.VehicleMove;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r2_10_11_12r1_12r2.ChunkData;
import protocolsupport.protocol.packet.middle.impl.clientbound.play.v_9r2_10_11_12r1_12r2.ChunkLight;
import protocolsupport.protocol.packet.middle.impl.clientbound.status.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.Pong;
import protocolsupport.protocol.packet.middle.impl.clientbound.status.v_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2_15_16r1_16r2_17r1_17r2_18.ServerInfo;
import protocolsupport.protocol.pipeline.IPacketDataChannelIO;
import protocolsupport.protocol.pipeline.version.util.encoder.AbstractPacketEncoder;
import protocolsupport.protocol.storage.netcache.NetworkDataCache;

public class PacketEncoder extends AbstractPacketEncoder<IClientboundMiddlePacketV11> {

	public PacketEncoder(IPacketDataChannelIO io, NetworkDataCache cache) {
		super(io, cache);
		registry.register(NetworkState.LOGIN, ClientBoundPacketType.LOGIN_SUCCESS, LoginSuccess::new);
		registry.register(NetworkState.LOGIN, ClientBoundPacketType.LOGIN_ENCRYPTION_BEGIN, EncryptionRequest::new);
		registry.register(NetworkState.LOGIN, ClientBoundPacketType.LOGIN_DISCONNECT, LoginDisconnect::new);
		registry.register(NetworkState.LOGIN, ClientBoundPacketType.LOGIN_SET_COMPRESSION, SetCompression::new);
		registry.register(NetworkState.STATUS, ClientBoundPacketType.STATUS_SERVER_INFO, ServerInfo::new);
		registry.register(NetworkState.STATUS, ClientBoundPacketType.STATUS_PONG, Pong::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_KEEP_ALIVE, KeepAlive::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_START_GAME, StartGame::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_CHAT, Chat::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_UPDATE_TIME, TimeUpdate::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_EQUIPMENT, EntityEquipment::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SPAWN_POSITION, SpawnPosition::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SET_HEALTH, SetHealth::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_RESPAWN, ChangeDimension::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_POSITION, SetPosition::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_HELD_SLOT, HeldSlot::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_ANIMATION, EntityAnimation::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SPAWN_NAMED, SpawnNamed::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_COLLECT_EFFECT, CollectEffect::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SPAWN_OBJECT, SpawnObject::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SPAWN_LIVING, SpawnLiving::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SPAWN_PAINTING, SpawnPainting::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SPAWN_EXP_ORB, SpawnExpOrb::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_VELOCITY, EntityVelocity::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_DESTROY, EntityDestroy::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_REL_MOVE, EntityRelMove::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_LOOK, EntityLook::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_REL_MOVE_LOOK, EntityRelMoveLook::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_TELEPORT, EntityTeleport::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_HEAD_ROTATION, EntityHeadRotation::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_STATUS, EntityStatus::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_LEASH, EntityLeash::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_METADATA, EntityMetadata::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_EFFECT_ADD, EntityEffectAdd::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_EFFECT_REMOVE, EntityEffectRemove::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SET_EXPERIENCE, SetExperience::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_ATTRIBUTES, EntityAttributes::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_SOUND, EntitySound::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_CHUNK_DATA, ChunkData::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_BLOCK_CHANGE_MULTI, BlockChangeMulti::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_BLOCK_CHANGE_SINGLE, BlockChangeSingle::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_BLOCK_BREAK_CONFIRM, BlockBreakConfirm::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_BLOCK_ACTION, BlockAction::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_BLOCK_BREAK_ANIMATION, BlockBreakAnimation::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_EXPLOSION, Explosion::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WORLD_EVENT, WorldEvent::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WORLD_SOUND, WorldSound::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WORLD_CUSTOM_SOUND, WorldCustomSound::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WORLD_PARTICLES, WorldParticle::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_GAME_STATE_CHANGE, GameStateChange::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WINDOW_OPEN, InventoryOpen::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WINDOW_HORSE_OPEN, InventoryHorseOpen::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WINDOW_CLOSE, InventoryClose::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WINDOW_SET_SLOT, InventorySetSlot::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WINDOW_SET_ITEMS, InventorySetItems::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WINDOW_DATA, InventoryData::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_UPDATE_MAP, UpdateMap::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_BLOCK_TILE, BlockTileUpdate::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SIGN_EDITOR, BlockOpenSignEditor::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_PLAYER_INFO, PlayerListSetEntry::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_PLAYER_ABILITIES, PlayerAbilities::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_TAB_COMPLETE, TabComplete::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SCOREBOARD_OBJECTIVE, ScoreboardObjective::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SCOREBOARD_SCORE, ScoreboardScore::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SCOREBOARD_DISPLAY_SLOT, ScoreboardDisplay::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SCOREBOARD_TEAM, ScoreboardTeam::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_CUSTOM_PAYLOAD, CustomPayload::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_RESOURCE_PACK, ResourcePack::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_KICK_DISCONNECT, KickDisconnect::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_CAMERA, Camera::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_PLAYER_LIST_HEADER_FOOTER, PlayerListHeaderFooter::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ENTITY_PASSENGERS, EntityPassengers::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_TITLE_TEXT, TitleText::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_TITLE_SUBTEXT, TitleSubText::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_TITLE_ANIMATION, TitleAnimation::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_TITLE_CLEAR, TitleClear::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ACTIONBAR, Actionbar::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WORLDBORDER_INIT, WorldBorderInit::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WORLDBORDER_CENTER, WorldBorderCenter::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WORLDBORDER_LERP_SIZE, WorldBorderLerpSize::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WORLDBORDER_SIZE, WorldBorderSize::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WORLDBORDER_WARN_DELAY, WorldBorderWarnDelay::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_WORLDBORDER_WARN_DISTANCE, WorldBorderWarnDistance::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_CHUNK_UNLOAD, ChunkUnload::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SERVER_DIFFICULTY, ServerDifficulty::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_COMBAT_BEGIN, CombatBegin::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_COMBAT_END, CombatEnd::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_COMBAT_DEATH, CombatDeath::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SET_COOLDOWN, SetCooldown::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_BOSS_BAR, BossBar::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_VEHICLE_MOVE, VehicleMove::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_CHUNK_LIGHT, ChunkLight::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_MERCHANT_TRADE_LIST, MerchantTradeList::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_BOOK_OPEN, BookOpen::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SYNC_PING, SyncPing::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_UNLOCK_RECIPES, NoopUnlockRecipes::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ADVANCEMENTS, NoopAdvancements::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_ADVANCEMENTS_TAB, NoopAdvanementsTab::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_CRAFT_RECIPE_CONFIRM, NoopCraftRecipeConfirm::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_STATISTICS, NoopStatistics::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_DECLARE_COMMANDS, NoopDeclareCommands::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_DECLARE_RECIPES, NoopDeclareRecipes::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_DECLARE_TAGS, NoopDeclareTags::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_STOP_SOUND, NoopStopSound::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_LOOK_AT, NoopLookAt::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_SET_VIEW_CENTER, NoopSetViewCenter::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_UPDATE_VIEW_DISTANCE, NoopUpdateViewDistance::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_VIBRATION, NoopVibration::new);
		registry.register(NetworkState.PLAY, ClientBoundPacketType.PLAY_UPDATE_SIMULATION_DISTANCE, NoopUpdateSimulationDistance::new);
	}

}
