package protocolsupport.compat;

import net.minecraft.server.v1_8_R3.PacketHandshakingInSetProtocol;
import protocolsupport.api.Connection;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ConnectionImpl;

public class CompatListener extends Connection.PacketListener {

    public ConnectionImpl connection;

    public CompatListener(ConnectionImpl c) {
        this.connection = c;
    }

    @Override
    public void onPacketReceiving(Connection.PacketListener.PacketEvent event) {
        try {
            // Check if we are getting handshake packet.
            if ((event.getPacket()) instanceof PacketHandshakingInSetProtocol && connection.getVersion() == ProtocolVersion.MINECRAFT_FUTURE) {
                final Object packet = event.getPacket();

                // ViaVersion has at this point already spoofed the connection version (since it is higher up the pipeline).
                // If via has put the protoVersion to the server we can spoof ProtocolSupport's version.
                connection.setVersion(ProtocolVersion.MINECRAFT_1_8);
            }
            // If version is not server version, ViaVersion will not spoof. ProtocolSupport will handle the rest.
            // In any case, remove the packet listener and wrap up.
            connection.removePacketListener(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
