package protocolsupport.api;

public enum ProtocolVersion {

	MINECRAFT_FUTURE(-1, 10),
	MINECRAFT_1_8(47, 9, "1.8.X"),
	MINECRAFT_15w14a(48, 9, "15w14a"),
	MINECRAFT_1_7_10(5, 8, "1.7.10"),
	MINECRAFT_1_7_5(4, 7, "1.7.2"),
	MINECRAFT_1_7_1(3, 7, "1.7.1"),
	MINECRAFT_1_6_4(78, 6, "1.6.4"),
	MINECRAFT_1_6_3(77, 6, "1.6.3"),
	MINECRAFT_1_6_2(74, 5, "1.6.2"),
	MINECRAFT_1_6_1(73, 4, "1.6.1"),
	MINECRAFT_1_6(72, 4, "1.6"),
	MINECRAFT_1_5_2(61, 3, "1.5.2"),
	MINECRAFT_2_0_Purple(92, 2, "2.0Purple"),
	MINECRAFT_2_0_Red(91, 2, "2.0Red"),
	MINECRAFT_2_0_Blue(90, 2, "2.0Blue"),
	MINECRAFT_1_5_1(60, 2, "1.5.1"),
	MINECRAFT_1_4_7(51, 1, "1.4.7"),
	MINECRAFT_1_4_5(49, 1, "1.4.5"),
	MINECRAFT_LEGACY(-1, 0),
	UNKNOWN(-1);

	private final int id;
	private final int orderId;
	private final String name;

	ProtocolVersion(int id) {
		this(id, -1);
	}

	ProtocolVersion(int id, int orderId) {
		this(id, orderId, null);
	}

	ProtocolVersion(int id, int orderId, String name) {
		this.id = id;
		this.orderId = orderId;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isSupported() {
		return name != null;
	}

	public boolean isAfter(ProtocolVersion another) {
		return orderId > another.orderId;
	}

	public boolean isAfterOrEq(ProtocolVersion another) {
		return orderId >= another.orderId;
	}

	public boolean isBefore(ProtocolVersion another) {
		return orderId < another.orderId;
	}

	public boolean isBeforeOrEq(ProtocolVersion another) {
		return orderId <= another.orderId;
	}

	public boolean isBetween(ProtocolVersion start, ProtocolVersion end) {
		int startId = Math.min(start.orderId, end.orderId);
		int endId = Math.max(start.orderId, end.orderId);
		return orderId >= startId && orderId <= endId;
	}

	private static final ProtocolVersion[] byOrderId = new ProtocolVersion[ProtocolVersion.values().length - 1];
	static {
		for (ProtocolVersion version : ProtocolVersion.values()) {
			if (version.orderId != -1) {
				byOrderId[version.orderId] = version;
			}
		}
	}

	// @Deprecated
	public static ProtocolVersion fromId(int id) {
		switch (id) {
			case 47: {
				return MINECRAFT_1_8;
			}
			case 48: {
				return MINECRAFT_15w14a;
			}
			case 5: {
				return MINECRAFT_1_7_10;
			}
			case 4: {
				return MINECRAFT_1_7_5;
			}
			case 3: {
				return MINECRAFT_1_7_1;
			}
			case 78: {
				return MINECRAFT_1_6_4;
			}
			case 77: {
				return MINECRAFT_1_6_3;
			}
			case 74: {
				return MINECRAFT_1_6_2;
			}
			case 73: {
				return MINECRAFT_1_6_1;
			}
			case 72: {
				return MINECRAFT_1_6;
			}
			case 61: {
				return MINECRAFT_1_5_2;
			}
			case 92: {
				return MINECRAFT_2_0_Purple;
			}
			case 91: {
				return MINECRAFT_2_0_Red;
			}
			case 90: {
				return MINECRAFT_2_0_Blue;
			}
			case 60: {
				return MINECRAFT_1_5_1;
			}
			case 51: {
				return MINECRAFT_1_4_7;
			}
			case 49: {
				return MINECRAFT_1_4_5;
			}
		}
		return UNKNOWN;
	}

	public static ProtocolVersion[] getAllBetween(ProtocolVersion start, ProtocolVersion end) {
		int startId = Math.min(start.orderId, end.orderId);
		int endId = Math.max(start.orderId, end.orderId);
		ProtocolVersion[] between = new ProtocolVersion[endId - startId + 1];
		for (int i = startId; i <= endId; i++) {
			between[i - startId] = byOrderId[i];
		}
		return between;
	}

	public static ProtocolVersion[] getAllBefore(ProtocolVersion version) {
		return getAllBetween(getOldest(), version);
	}

	public static ProtocolVersion[] getAllAfter(ProtocolVersion version) {
		return getAllBetween(version, getLatest());
	}

	public static ProtocolVersion getLatest() {
		return ProtocolVersion.MINECRAFT_1_8;
	}

	public static ProtocolVersion getOldest() {
		return ProtocolVersion.MINECRAFT_1_4_5;
	}

}