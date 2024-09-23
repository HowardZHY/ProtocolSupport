package protocolsupport.api;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

import com.google.common.base.Objects;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.channel.ChannelPipeline;
import org.bukkit.entity.Player;
import protocolsupport.protocol.core.ChannelHandlers;
import protocolsupport.protocol.core.IPacketPrepender;
import protocolsupport.protocol.core.IPacketSplitter;
import protocolsupport.protocol.core.wrapped.WrappedPrepender;
import protocolsupport.protocol.core.wrapped.WrappedSplitter;

public abstract class Connection {

    public volatile ProtocolVersion version;

    public Connection(ProtocolVersion version) {
        this.version = version;
    }

    public abstract Object getNetworkManager();

    public abstract boolean isConnected();

    public abstract InetSocketAddress getRawAddress();

    public abstract InetSocketAddress getAddress();

    public abstract void changeAddress(InetSocketAddress newRemote);

    public abstract Player getPlayer();

    public ProtocolVersion getVersion() {
        return version;
    }

    public abstract void receivePacket(Object packet) throws ExecutionException;

    public abstract void sendPacket(Object packet);

    public abstract void sendRawPacket(byte[] data);

    public abstract void receiveRawPacket(byte[] data);

    protected final CopyOnWriteArrayList<PacketListener> packetlisteners = new CopyOnWriteArrayList<>();

    public void addPacketListener(PacketListener listener) {
        packetlisteners.add(listener);
    }

    public void removePacketListener(PacketListener listener) {
        packetlisteners.remove(listener);
    }

    protected final CopyOnWriteArrayList<PacketSendListener> sendListeners = new CopyOnWriteArrayList<>();

    protected final CopyOnWriteArrayList<PacketReceiveListener> receiveListeners = new CopyOnWriteArrayList<>();

    public void addPacketSendListener(PacketSendListener listener) {
        sendListeners.add(listener);
    }

    public void removePacketSendListener(PacketSendListener listener) {
        sendListeners.remove(listener);
    }

    public void addPacketReceiveListener(PacketReceiveListener listener) {
        receiveListeners.add(listener);
    }

    public void removePacketReceiveListener(PacketReceiveListener listener) {
        receiveListeners.remove(listener);
    }

    private final ConcurrentHashMap<String, Object> metadata = new ConcurrentHashMap<>();

    public void addMetadata(String key, Object obj) {
        metadata.put(key, obj);
    }

    public Object getMetadata(String key) {
        return metadata.get(key);
    }

    public Object removeMetadata(String key) {
        return metadata.remove(key);
    }

    public boolean hasMetadata(String key) {
        return metadata.containsKey(key);
    }

    public abstract static class PacketListener {

        /**
         * Override to handle native packet sending
         * Note: PacketEvent and it's data is only valid while handling the packet
         * @param event packet event
         */
        public void onPacketSending(PacketEvent event) {
        }

        /**
         * Override to handle native packet receiving
         * Note: PacketEvent and it's data is only valid while handling the packet
         * Note: Based on client version this the received data might be a part of packet, not a full one
         * @param event packet event
         */
        public void onPacketReceiving(PacketEvent event) {
        }

        /**
         * Override to handle raw packet sending
         * Note: PacketEvent and it's data is only valid while handling the packet
         * @param event packet event
         */
        public void onRawPacketSending(RawPacketEvent event) {
        }

        /**
         * Override to handle raw packet sending
         * @param event packet event
         */
        public void onRawPacketReceiving(RawPacketEvent event) {
        }

        public static class PacketEvent {

            protected Object packet;
            protected boolean cancelled;

            /**
             * Returns packet
             * @return native packet instance
             */
            public Object getPacket() {
                return packet;
            }

            /**
             * Sets packet
             * @param packet native packet instance
             */
            public void setPacket(Object packet) {
                this.packet = packet;
            }

            /**
             * Returns if packet is cancelled
             * @return true if packet is cancelled, false otherwise
             */
            public boolean isCancelled() {
                return cancelled;
            }

            /**
             * Sets if packet is cancelled
             * @param cancelled true if packet is cancelled, false otherwise
             */
            public void setCancelled(boolean cancelled) {
                this.cancelled = cancelled;
            }
        }

        public static class RawPacketEvent {

            protected ByteBuf data;
            protected boolean cancelled;

            /**
             * Returns read only packet data
             * @return read only packet data
             */
            public ByteBuf getData() {
                return new ReadOnlyByteBuf(data);
            }

            /**
             * Sets packet data
             * A copy of passed ByteBuf is made, and passed ByteBuf is not released
             * @param data packet data
             */
            public void setData(ByteBuf data) {
                this.data.release();
                this.data = data.copy();
            }

            /**
             * Returns if packet is cancelled
             * @return true if packet is cancelled, false otherwise
             */
            public boolean isCancelled() {
                return cancelled;
            }

            /**
             * Sets if packet is cancelled
             * @param cancelled true if packet is cancelled, false otherwise
             */
            public void setCancelled(boolean cancelled) {
                this.cancelled = cancelled;
            }

        }

    }

    private static class DeprecatedPacketListener extends PacketListener {
        private final Object deprecatedlistener;
        public DeprecatedPacketListener(Object deprecatedlistener) {
            this.deprecatedlistener = deprecatedlistener;
        }
        @Override
        public int hashCode() {
            return deprecatedlistener.hashCode();
        }
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DeprecatedPacketListener) {
                return Objects.equal(deprecatedlistener, ((DeprecatedPacketListener) obj).deprecatedlistener);
            }
            return false;
        }
    }

    @FunctionalInterface
    public static interface PacketSendListener {
        public boolean onPacketSending(Object packet);
    }

    @FunctionalInterface
    public static interface PacketReceiveListener {
        public boolean onPacketReceiving(Object packet);
    }

    public void setFraming(ChannelPipeline pipeline, IPacketSplitter splitter, IPacketPrepender prepender) {
        ((WrappedSplitter) pipeline.get(ChannelHandlers.SPLITTER)).setRealSplitter(splitter);
        ((WrappedPrepender) pipeline.get(ChannelHandlers.PREPENDER)).setRealPrepender(prepender);
    }
}