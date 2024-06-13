package com.example.starter.utils;

import com.example.starter.model.mapper.Response;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;


public enum ResponseUtils {
INSTANCE;

//public static final Logger logger= (Logger) LoggerFactory.getLogger(ResponseUtils.class);

  public void writeJsonResponse(RoutingContext context, Object object, String status) {
    try {
      context.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        .end(JsonObject.mapFrom(object).put("status", status).encode());
    } catch (Exception e) {
//      logger.error("Exception in writeJsonResponse : {}", e);
      System.out.println(e);
    }
  }

  public void writeJsonResponse(RoutingContext context, Object object) {
    try {
      context.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        .end(JsonObject.mapFrom(object).put("status", "success").encode());
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }


  public void writeJsonErrorResponse(RoutingContext context, String status, int statusCode) {
    try {
      context.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        .setStatusCode(statusCode)
        .end(JsonObject.mapFrom(status).put("status", "fail").encode());
    } catch (Exception e) {
      System.out.println(e);    }
  }


  public void writeJsonErrorResponse(RoutingContext context, Object data, int statusCode) {
    try {
      context.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        .setStatusCode(statusCode)
        .end(JsonObject.mapFrom(data).put("status", "fail").encode());
    } catch (Exception e) {
      System.out.println(e);
    }
  }


    public void handleError(RoutingContext context, Throwable error) {
    }
}














