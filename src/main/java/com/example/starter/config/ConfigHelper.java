package com.example.starter.config;

import io.vertx.core.json.JsonObject;

public enum ConfigHelper {
	INSTANCE;

	public JsonObject getMySqlConfig() {
		return ConfigManager.INSTANCE.getMainConfig().getJsonObject("sql");
	}

	public JsonObject getGenderizeConfig() {
		return ConfigManager.INSTANCE.getMainConfig()
				.getJsonObject("external").getJsonObject("genderize");
	}

	public JsonObject getExternalConfig() {
		return ConfigManager.INSTANCE.getMainConfig().getJsonObject("external");
	}

	public JsonObject getAuthConfig() {
		return ConfigManager.INSTANCE.getMainConfig().getJsonObject("auth");
	}
}
