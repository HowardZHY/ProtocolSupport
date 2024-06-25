package protocolsupport.protocol.pipeline.common;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;

import com.mojang.authlib.GameProfile;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.ReadTimeoutException;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.PacketListener;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.events.PlayerDisconnectEvent;
import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.storage.ProtocolStorage;
import protocolsupport.protocol.transformer.handlers.AbstractLoginListener;
import protocolsupport.utils.netty.ChannelUtils;

public class LogicHandler extends ChannelDuplexHandler {

    private static final HashMap<Class<? extends Throwable>, Set<?>> ignoreExceptions = new HashMap<>();

    private final ConnectionImpl connection;

    static {
        ignoreExceptions.put(ClosedChannelException.class, Collections.emptySet());
        ignoreExceptions.put(ReadTimeoutException.class, Collections.emptySet());
        ignoreExceptions.put(IOException.class, new HashSet<>(Arrays.asList("Connection reset by peer", "Broken pipe")));
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public LogicHandler(ConnectionImpl connection) {
        this.connection = connection;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        msg = connection.handlePacketReceive(msg);
        if (msg == null) {
            return;
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        msg = connection.handlePacketSend(msg);
        if (msg == null) {
            promise.setSuccess();
            return;
        }
        super.write(ctx, msg, promise);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        super.exceptionCaught(ctx, e);
        Set<?> ignore = ignoreExceptions.get(e.getClass());
        if ((ignore == null) || (!ignore.isEmpty() && !ignore.contains(e.getMessage()))) {
            SocketAddress remoteaddr = ChannelUtils.getNetworkManagerSocketAddress(ctx.channel());
            LOGGER.info(e + remoteaddr.toString() + ProtocolSupportAPI.getProtocolVersion(remoteaddr));
        }
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        NetworkManager networkManager = ChannelUtils.getNetworkManager(ctx.channel());
        InetSocketAddress addr = (InetSocketAddress) networkManager.getSocketAddress();
        String username = null;
        PacketListener listener = networkManager.getPacketListener();
        if (listener instanceof AbstractLoginListener) {
            GameProfile profile = ((AbstractLoginListener) listener).getProfile();
            if (profile != null) {
                username = profile.getName();
            }
        } else if (listener instanceof PlayerConnection) {
            username = ((PlayerConnection) listener).player.getProfile().getName();
        }
        if (username != null) {
            PlayerDisconnectEvent event = new PlayerDisconnectEvent(addr, username);
            Bukkit.getPluginManager().callEvent(event);
        }
        ProtocolStorage.removeConnection(addr);
    }
}