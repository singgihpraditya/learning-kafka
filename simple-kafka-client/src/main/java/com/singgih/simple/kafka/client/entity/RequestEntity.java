package com.singgih.simple.kafka.client.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.singgih.simple.kafka.client.util.Util;

public class RequestEntity {
	@SerializedName(value = "id")
	public String id;

	public RequestEntity() {
		this.id = Util.getID();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
