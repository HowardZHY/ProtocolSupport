package com.viaversion.viaversion.bukkit.platform;

import com.viaversion.viaversion.ViaAPIBase;
import com.viaversion.viaversion.ViaVersionPlugin;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.bukkit.util.ProtocolSupportUtil;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BukkitViaAPI extends ViaAPIBase<Player> {

    @Override
    public ProtocolVersion getPlayerProtocolVersion(Player player) {
        return this.getPlayerProtocolVersion(player.getUniqueId());
    }

    @Override
    public ProtocolVersion getPlayerProtocolVersion(UUID uuid) {
        UserConnection connection = Via.getManager().getConnectionManager().getServerConnection(uuid);
        if (connection != null) {
            return connection.getProtocolInfo().protocolVersion();
        }
        if (ViaVersionPlugin.isProtocolSupport()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                return ProtocolSupportUtil.getProtocolVersion(player);
            }
        }
        return ProtocolVersion.unknown;
    }

    public void sendRawPacket(Player player, ByteBuf packet) throws IllegalArgumentException {
        this.sendRawPacket(player.getUniqueId(), packet);
    }
}