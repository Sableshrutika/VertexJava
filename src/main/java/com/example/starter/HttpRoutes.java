package com.example.starter;
import com.example.starter.Controller.AddEmployee;
import com.example.starter.Controller.GetAllEmployeController;
import com.example.starter.Controller.GetEmployeeById;
import com.example.starter.External.Controller.GetAllProduct;
import com.example.starter.config.ConfigManager;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

import io.vertx.ext.web.handler.BodyHandler;


public class HttpRoutes extends AbstractVerticle {

  public static final Logger logger= LoggerFactory.getLogger(HttpRoutes.class);
  private HttpServer httpServer;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    HttpServerOptions httpServerOptions=new HttpServerOptions();
    httpServerOptions.setCompressionSupported(true);
    httpServer = vertx
      .createHttpServer(httpServerOptions)
      .requestHandler(router)
      .listen(ConfigManager.INSTANCE.getMainConfig().getInteger("port"), httpListenHandler -> {

        if(httpListenHandler.succeeded()){
       logger.info("http server started on port :{}",ConfigManager.INSTANCE.getMainConfig().getInteger("port"));
       startPromise.complete();
       }
       else{
        logger.info("Http sever failed to start :{}",httpListenHandler.cause());
        startPromise.fail(httpListenHandler.cause().getMessage());
        }

      });


try {
  createRoutes(router);
}
catch(Exception e){
logger.error("Exception:{}",e);
}
}
//  StudentController.INSTANCE.router(router);



  public  void createRoutes(final Router router){
  router.post("/employee/save").handler(AddEmployee.INSTANCE::handle);
  router.get("/employee").handler(GetAllEmployeController.INSTANCE::handle);
  router.get("/getProduct").handler(GetAllProduct.INSTANCE::handle);
  router.get("/employee/:employeeId").handler(GetEmployeeById.Instance::handle);
  }
  private int getProcessors() {
    return Math.max(1, Runtime.getRuntime().availableProcessors());
  }
  }


