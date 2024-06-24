package protocolsupport.protocol;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.CancelledPacketHandleException;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketListener;
import protocolsupport.api.Connection;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.utils.netty.ChannelUtils;

public class ConnectionImpl extends Connection {

    private final NetworkManager networkmanager;
    public ConnectionImpl(NetworkManager networkmanager, ProtocolVersion version) {
        super(version);
        this.networkmanager = networkmanager;
    }

    @Override
    public Object getNetworkManager() {
        return networkmanager;
    }

    @Override
    public boolean isConnected() {
        return networkmanager.g();
    }

    @Override
    public InetSocketAddress getAddress() {
        return (InetSocketAddress) networkmanager.getSocketAddress();
    }

    @Override
    public Player getPlayer() {
        return ChannelUtils.getBukkitPlayer(networkmanager);
    }

    @Override
    public void receivePacket(Object packet) throws ExecutionException {
        @SuppressWarnings("unchecked")
        final Packet<PacketListener> packetInst = (Packet<PacketListener>) packet;
        Runnable packetReceive = () -> {
            if (networkmanager.channel.isOpen()) {
                try {
                    packetInst.a(networkmanager.getPacketListener());
                } catch (CancelledPacketHandleException ignored) {
                }
            }
        };
        if (networkmanager.channel.eventLoop().inEventLoop()) {
            try {
                packetReceive.run();
            } catch (Throwable t) {
                throw new ExecutionException(t);
            }
        } else {
            try {
                networkmanager.channel.eventLoop().submit(packetReceive).get();
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    public void sendPacket(Object packet) {
        networkmanager.handle((Packet<?>) packet);
    }

    public void setProtocolVersion(ProtocolVersion version) {
        this.version = version;
    }

    public boolean handlePacketSend(Object packet) {
        boolean canSend = true;
        for (PacketSendListener listener : sendListeners) {
            if (!listener.onPacketSending(packet)) {
                canSend = false;
            }
        }
        return canSend;
    }

    public boolean handlePacketReceive(Object packet) {
        boolean canReceive = true;
        for (PacketReceiveListener listener : receiveListeners) {
            if (!listener.onPacketReceiving(packet)) {
                canReceive = false;
            }
        }
        return canReceive;
    }

}