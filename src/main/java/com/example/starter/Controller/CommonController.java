package com.example.starter.Controller;


import io.vertx.ext.web.RoutingContext;

@FunctionalInterface
public interface CommonController {
  void handle(RoutingContext context);//due to functional interface this interface contain only one abstarct method

  default  void fail(String msg){
    throw  new RuntimeException(msg);
  }
}
