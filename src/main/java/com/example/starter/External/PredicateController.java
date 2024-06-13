package com.example.starter.External;

import com.example.starter.Controller.CommonController;
import com.example.starter.config.ConfigHelper;
import com.example.starter.model.mapper.Response;
import com.example.starter.utils.ResponseUtils;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public enum PredicateController implements CommonController {
  INSTANCE;
  private static final Logger logger=LoggerFactory.getLogger(PredicateController.class);

  @Override
  public void handle(RoutingContext context) {
    Response rs=new Response();
    try {
    List<String>name=context.queryParam("name");
    String baseUrl= ConfigHelper.INSTANCE.getGenderizeConfig().getString("baseUrl");
    retrofit2.Response<PredicateGenderResponse> jsonResponse = ApiClientManager.INSTANCE.getApiClient(baseUrl, GenderizeService.class)
      .getGenderFromName(name.get(0))
      .execute();

     if(jsonResponse.isSuccessful()){
      logger.info("Api Response from client :{}",jsonResponse.body());
       rs.setMessage("Data Fetch successfully ");
       rs.setData(jsonResponse.body());
       ResponseUtils.INSTANCE.writeJsonResponse(context,rs);
     } else {
       assert jsonResponse.errorBody() != null;
       rs.setErrors(Collections.singletonList(jsonResponse.errorBody().string()));
       ResponseUtils.INSTANCE.writeJsonErrorResponse(context, rs, jsonResponse.code());
     }
    }

    catch (Exception e) {
      rs.setErrors(Collections.singletonList(e.getMessage()));
      logger.error("Error in API response : {}", e);
      ResponseUtils.INSTANCE.writeJsonErrorResponse(context, rs, 500);
    }

  }

  @Override
  public void fail(String msg) {
    CommonController.super.fail(msg);
  }
}
