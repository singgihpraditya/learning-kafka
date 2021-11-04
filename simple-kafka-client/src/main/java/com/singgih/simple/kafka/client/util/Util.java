package com.singgih.simple.kafka.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
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
	
	public static Properties getProperties() {
		Properties properties = new Properties();
		try {
			InputStream inputStream = Util.class.getClassLoader().getResourceAsStream("kafka-client.properties");
			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
}
