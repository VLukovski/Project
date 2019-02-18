package com.qa.util;

import com.google.gson.Gson;

public class JSONUtil {

	private Gson gson;

	public JSONUtil() {
		this.gson = new Gson();
	}

	public String getJsonForObject(Object object) {
		return gson.toJson(object);
	}

	public <T> T getObjectForJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}
}
