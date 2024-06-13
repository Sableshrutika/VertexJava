package com.example.starter.External.Controller;

import com.example.starter.Controller.CommonController;
import com.example.starter.External.response.Product;
import com.example.starter.External.service.ProductServicImplemention;
import com.example.starter.model.mapper.Response;
import com.example.starter.utils.ResponseUtils;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public enum GetAllProduct implements CommonController {
  INSTANCE;
  public static final Logger logger= LoggerFactory.getLogger(GetAllProduct.class);

  @Override
  public void handle(RoutingContext context) {
    Response response=new Response();
    try {
      retrofit2.Response<List<Product>>execute= ProductServicImplemention.INSTANCE.FakeStoreApi()
        .getAllProduct().execute();
       if(execute.isSuccessful() && execute.body()!=null){
      logger.info("product size :{} ",execute.body().size());
       response.setData(execute.body());
       response.setMessage("All product Featch SuccessFully!!");
         ResponseUtils.INSTANCE.writeJsonResponse(context,response);

       }
       else{
        response.setErrors(Collections.singletonList("error in api"));
        response.setMessage("exception occured");
        ResponseUtils.INSTANCE.writeJsonErrorResponse(context,response,500);

       }

    } catch (IOException e) {
      logger.error("error in GetAllController : {}",e);
       response.setMessage(e.getMessage());
       response.setErrors(Collections.singletonList(e.getMessage()));
       ResponseUtils.INSTANCE.writeJsonErrorResponse(context,response,500);
    }


  }

  @Override
  public void fail(String msg) {
    CommonController.super.fail(msg);
  }
}
