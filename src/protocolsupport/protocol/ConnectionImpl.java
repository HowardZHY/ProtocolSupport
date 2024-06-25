package protocolsupport.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.v1_8_R3.NetworkManager;
import org.bukkit.entity.Player;
import protocolsupport.api.Connection;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.core.ChannelHandlers;
import protocolsupport.utils.netty.ChannelUtils;

import java.net.InetSocketAddress;

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
    public void sendPacket(Object packet) {
        runTask(() -> {
            try {
                ChannelHandlerContext ctx = networkmanager.channel.pipeline().context(ChannelHandlers.LOGIC);
                ctx.writeAndFlush(packet);
            } catch (Throwable t) {
                System.err.println("Error occured while packet sending");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void receivePacket(Object packet) {
        runTask(() -> {
            try {
                ChannelHandlerContext ctx = networkmanager.channel.pipeline().context(ChannelHandlers.LOGIC);
                ctx.fireChannelRead(packet);
            } catch (Throwable t) {
                System.err.println("Error occured while packet receiving");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void sendRawPacket(byte[] data) {
        ByteBuf dataInst = Unpooled.wrappedBuffer(data);
        runTask(() -> {
            try {
                ChannelHandlerContext ctx = networkmanager.channel.pipeline().context(ChannelHandlers.RAW_CAPTURE_SEND);
                ctx.writeAndFlush(dataInst);
            } catch (Throwable t) {
                System.err.println("Error occured while raw packet sending");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void receiveRawPacket(byte[] data) {
        ByteBuf dataInst = Unpooled.wrappedBuffer(data);
        runTask(() -> {
            try {
                ChannelHandlerContext ctx = networkmanager.channel.pipeline().context(ChannelHandlers.RAW_CAPTURE_RECEIVE);
                ctx.fireChannelRead(dataInst);
            } catch (Throwable t) {
                System.err.println("Error occured while raw packet receiving");
                t.printStackTrace();
            }
        });
    }

    private void runTask(Runnable task) {
        if (networkmanager.channel.eventLoop().inEventLoop()) {
            task.run();
        } else {
            networkmanager.channel.eventLoop().submit(task);
        }
    }

    public void setVersion(ProtocolVersion version) {
        this.version = version;
    }

    public void setProtocolVersion(ProtocolVersion version) {
        this.version = version;
    }

    private final LPacketEvent packetevent = new LPacketEvent();

    private static class LPacketEvent extends PacketListener.PacketEvent {
        public void init(Object packet) {
            this.packet = packet;
            this.cancelled = false;
        }
    }

    public Object handlePacketSend(Object packet) {
        packetevent.init(packet);
        for (PacketListener listener : packetlisteners) {
            try {
                listener.onPacketSending(packetevent);
            } catch (Throwable t) {
                System.err.println("Error occured while handling packet sending");
                t.printStackTrace();
            }
        }
        return packetevent.isCancelled() ? null : packetevent.getPacket();
    }

    public Object handlePacketReceive(Object packet) {
        packetevent.init(packet);
        for (PacketListener listener : packetlisteners) {
            try {
                listener.onPacketReceiving(packetevent);
            } catch (Throwable t) {
                System.err.println("Error occured while handling packet receiving");
                t.printStackTrace();
            }
        }
        return packetevent.isCancelled() ? null : packetevent.getPacket();
    }

    private final LRawPacketEvent rawpacketevent = new LRawPacketEvent();

    private static class LRawPacketEvent extends PacketListener.RawPacketEvent {
        public void init(ByteBuf data) {
            this.data = data;
            this.cancelled = false;
        }
        public ByteBuf getDirectData() {
            return this.data;
        }
    }

    public ByteBuf handleRawPacketSend(ByteBuf data) {
        rawpacketevent.init(data);
        for (PacketListener listener : packetlisteners) {
            try {
                listener.onRawPacketSending(rawpacketevent);
            } catch (Throwable t) {
                System.err.println("Error occured while handling raw packet sending");
                t.printStackTrace();
            }
        }
        if (rawpacketevent.isCancelled()) {
            rawpacketevent.getDirectData().release();
            return null;
        } else {
            return rawpacketevent.getDirectData();
        }
    }

    public ByteBuf handleRawPacketReceive(ByteBuf data) {
        rawpacketevent.init(data);
        for (PacketListener listener : packetlisteners) {
            try {
                listener.onRawPacketReceiving(rawpacketevent);
            } catch (Throwable t) {
                System.err.println("Error occured while handling raw packet receiving");
                t.printStackTrace();
            }
        }
        if (rawpacketevent.isCancelled()) {
            rawpacketevent.getDirectData().release();
            return null;
        } else {
            return rawpacketevent.getDirectData();
        }
    }

}