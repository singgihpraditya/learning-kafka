package com.singgih.image.management.util;

import java.util.UUID;

public class Util {
	public static String getHashCode(String serviceName) {
		String hashCode = UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 8);
		return "[" + hashCode + "]" + serviceName + " ";
	}

	public static String getHashCode() {
		return getHashCode("");
	}

	public static String getID() {
		String id = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		return id;
	}
}
