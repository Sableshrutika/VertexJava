package com.example.starter.v2.external;

import com.example.starter.utils.SubRouter;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import sun.security.provider.certpath.Vertex;

public enum MountExternalRouter implements SubRouter {
  INSTANCE;

  @Override
  public Router router(Vertx vertx) {
    Router router=Router.router(vertx);
    router.get("/gender").handler(PredicateGenderController.INSTANCE::handle) ;
    return router;
  }
}
