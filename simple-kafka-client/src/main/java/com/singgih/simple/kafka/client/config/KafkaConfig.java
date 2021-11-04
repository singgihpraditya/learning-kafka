package com.singgih.simple.kafka.client.config;

public class KafkaConfig {
	private String kafkaBrokersAddress;
	private String user;
	private String pass;
	private String principalName;
	private String serviceName;
	private String keyTabFileLocation;
	private String krb5ConfigFileLocation;

	public String getKafkaBrokersAddress() {
		return kafkaBrokersAddress;
	}

	public void setKafkaBrokersAddress(String kafkaBrokersAddress) {
		this.kafkaBrokersAddress = kafkaBrokersAddress;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getKeyTabFileLocation() {
		return keyTabFileLocation;
	}

	public void setKeyTabFileLocation(String keyTabFileLocation) {
		this.keyTabFileLocation = keyTabFileLocation;
	}

	public String getKrb5ConfigFileLocation() {
		return krb5ConfigFileLocation;
	}

	public void setKrb5ConfigFileLocation(String krb5ConfigFileLocation) {
		this.krb5ConfigFileLocation = krb5ConfigFileLocation;
	}

}
