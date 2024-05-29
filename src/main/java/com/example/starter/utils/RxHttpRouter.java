package com.example.starter.utils;


import com.example.starter.config.ConfigManager;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerOptions;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RxHttpRouter extends AbstractVerticle {


  private static final Logger log = LoggerFactory.getLogger(RxHttpRouter.class);

  public void start(Future<Void> startFuture) throws Exception {
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    HttpServerOptions serverOptions = new HttpServerOptions();
    serverOptions.setCompressionSupported(true);
    vertx.createHttpServer(serverOptions)
      .requestHandler(router)
      .listen(ConfigManager.INSTANCE.getMainConfig().getInteger("port"), httpListenHandler -> {
        if (httpListenHandler.succeeded()) {
          log.info("RxHttp server started on port : {}", ConfigManager.INSTANCE.getMainConfig().getInteger("port"));
          try {
            super.start();

          } catch (Exception e) {
            log.error("Error in RxHttp Server : {}", e);
          }
        } else {
          log.info("RxHttp server failed to start : {}", httpListenHandler.cause());
          try {
            throw startFuture.cause();
          } catch (Throwable e) {
            log.error("Error in RxHttp Serer : {}", e);
          }
        }
      });


  }
}
