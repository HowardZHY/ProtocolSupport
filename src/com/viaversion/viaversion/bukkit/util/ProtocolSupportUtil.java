package com.viaversion.viaversion.bukkit.util;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.api.protocol.version.SubVersionRange;
import com.viaversion.viaversion.api.protocol.version.VersionType;
import org.bukkit.entity.Player;
import protocolsupport.api.ProtocolSupportAPI;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class ProtocolSupportUtil {

    public static final Set<Integer> LEGACY_PROTOCOL_IDS = new HashSet<>();

    public static final ProtocolVersion r1_3_1tor1_3_2 = registerLegacy(39, "1.3.1-1.3.2", new SubVersionRange("1.3", 1, 2));
    public static final ProtocolVersion r1_4_2 = registerLegacy(47, "1.4.0-1.4.2", new SubVersionRange("1.4", 0, 2));
    public static final ProtocolVersion r1_4_4tor1_4_5 = registerLegacy(49, "1.4.4-1.4.5", new SubVersionRange("1.4", 4, 5));
    public static final ProtocolVersion r1_4_6tor1_4_7 = registerLegacy(51, "1.4.6-1.4.7", new SubVersionRange("1.4", 6, 7));
    public static final ProtocolVersion r1_5tor1_5_1 = registerLegacy(60, "1.5-1.5.1", new SubVersionRange("1.5", 0, 1));
    public static final ProtocolVersion r1_5_2 = registerLegacy(61, "1.5.2");
    public static final ProtocolVersion r1_6 = registerLegacy(72, "1.6.0");
    public static final ProtocolVersion r1_6_1 = registerLegacy(73, "1.6.1");
    public static final ProtocolVersion r1_6_2 = registerLegacy(74, "1.6.2");
    public static final ProtocolVersion r1_6_3 = registerLegacy(77, "1.6.3");
    public static final ProtocolVersion r1_6_4 = registerLegacy(78, "1.6.4");

    private static ProtocolVersion registerLegacy(final int version, final String name) {
        return registerLegacy(version, name, null);
    }

    private static ProtocolVersion registerLegacy(final int version, final String name, final SubVersionRange versionRange) {
        LEGACY_PROTOCOL_IDS.add(version);
        final ProtocolVersion protocolVersion = new ProtocolVersion(VersionType.RELEASE_INITIAL, version, -1, name, versionRange);
        ProtocolVersion.register(protocolVersion);
        return protocolVersion;
    }

    public static ProtocolVersion getProtocolVersion(Player player) {
        try {
            final protocolsupport.api.ProtocolVersion version = ProtocolSupportAPI.getProtocolVersion(player);
            final int id = version.getId();
            final boolean preNetty = LEGACY_PROTOCOL_IDS.contains(id);
            return ProtocolVersion.getProtocol(preNetty ? VersionType.RELEASE_INITIAL : VersionType.RELEASE, id);
        } catch (Exception e) {
            Via.getPlatform().getLogger().log(Level.SEVERE, "Failed to get ProtocolSupport version", e);
        }
        return ProtocolVersion.unknown;
    }

}
