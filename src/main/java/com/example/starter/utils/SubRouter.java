package com.example.starter.utils;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import sun.security.provider.certpath.Vertex;

public interface SubRouter {
  Router router(Vertx vertx);
}
