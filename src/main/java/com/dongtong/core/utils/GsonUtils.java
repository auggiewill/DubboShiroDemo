package com.dongtong.core.utils;

import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;

public class GsonUtils {

	public static final GsonBuilder INSTANCE = new GsonBuilder();
	
	public static Gson create() {
	    return INSTANCE.create();
	}
	
	
	public static JsonObject toJson(Object src){
		return toJson(create().toJson(src));
	}
	
	public static JsonObject toJson(String content){
		JsonElement element = Streams.parse(new JsonReader(new StringReader(content)));
		return element.getAsJsonObject();
	}
}
