package com.example.starter.config;

import io.vertx.core.json.JsonObject;


public enum ConfigManager {
	INSTANCE;

	private JsonObject mainConfig;

	public void setMainConfig(JsonObject config){
		this.mainConfig = config;
	}

	public JsonObject getMainConfig(){
		return this.mainConfig;
	}

}
