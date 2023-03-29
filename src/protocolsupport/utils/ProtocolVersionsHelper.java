package protocolsupport.utils;

import java.util.ArrayList;
import java.util.Arrays;

import protocolsupport.api.ProtocolVersion;

public class ProtocolVersionsHelper {

	public static final ProtocolVersion[] BEFORE_1_5 = new ProtocolVersion[] {
		ProtocolVersion.MINECRAFT_1_4_5, ProtocolVersion.MINECRAFT_1_4_7
	};

	public static final ProtocolVersion[] IS_2_0 = new ProtocolVersion[] {
			ProtocolVersion.MINECRAFT_2_0_Blue, ProtocolVersion.MINECRAFT_2_0_Red, ProtocolVersion.MINECRAFT_2_0_Purple
	};

	public static final ProtocolVersion[] BEFORE_1_6_EXCLUDE_2_0 = concat(
		BEFORE_1_5,
		ProtocolVersion.MINECRAFT_1_5_1, ProtocolVersion.MINECRAFT_1_5_2
	);

	public static final ProtocolVersion[] BEFORE_1_6 = concat(
		BEFORE_1_5,
		ProtocolVersion.MINECRAFT_1_5_1, ProtocolVersion.MINECRAFT_2_0_Blue, ProtocolVersion.MINECRAFT_2_0_Red, ProtocolVersion.MINECRAFT_2_0_Purple, ProtocolVersion.MINECRAFT_1_5_2
	);

	public static final ProtocolVersion[] BEFORE_1_7 = concat(
		BEFORE_1_6,
		ProtocolVersion.MINECRAFT_1_6, ProtocolVersion.MINECRAFT_1_6_1, ProtocolVersion.MINECRAFT_1_6_2, ProtocolVersion.MINECRAFT_1_6_3, ProtocolVersion.MINECRAFT_1_6_4
	);

	public static final ProtocolVersion[] BEFORE_1_8 = concat(
		BEFORE_1_7,
		ProtocolVersion.MINECRAFT_1_7_1, ProtocolVersion.MINECRAFT_1_7_5, ProtocolVersion.MINECRAFT_1_7_10
	);

	public static final ProtocolVersion[] IS_15W14A = new ProtocolVersion[] {
		ProtocolVersion.MINECRAFT_15w14a
	};

	public static final ProtocolVersion[] BEFORE_1_9 = concat(BEFORE_1_8, ProtocolVersion.MINECRAFT_15w14a, ProtocolVersion.MINECRAFT_1_8);

	public static final ProtocolVersion[] ALL = BEFORE_1_9;



	public static final ProtocolVersion[] concat(ProtocolVersion[] versions, ProtocolVersion... moreVersions) {
		ArrayList<ProtocolVersion> all = new ArrayList<ProtocolVersion>();
		all.addAll(Arrays.asList(versions));
		all.addAll(Arrays.asList(moreVersions));
		return all.toArray(new ProtocolVersion[all.size()]);
	}

}
