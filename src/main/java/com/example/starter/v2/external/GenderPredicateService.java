package com.example.starter.v2.external;

import com.example.starter.External.ApiClientManager;
import com.example.starter.External.GenderizeService;
import com.example.starter.External.PredicateGenderResponse;
import com.example.starter.admin.User.request.NewControllerClass;
import com.example.starter.config.ConfigHelper;
import com.example.starter.exception.RoutingError;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

public enum GenderPredicateService  {
  INSTANCE;

  public static final Logger log= LoggerFactory.getLogger(GenderPredicateService.class);

public PredicateGenderResponse getGenderByName(String name) {
  PredicateGenderResponse response = null;
  try {
    String baseUrl = ConfigHelper.INSTANCE.getGenderizeConfig().getString("baseUrl");
    Response<PredicateGenderResponse> jsonResponse = ApiClientManager.INSTANCE.getApiClient(baseUrl, GenderizeService.class)
      .getGenderFromName(name)
      .execute();

    if(jsonResponse.isSuccessful()){
       response=jsonResponse.body();
      }
  }
  catch (Exception e) {
    System.out.println(e);
    throw new RoutingError(e.getMessage());
  }
  return  response;
}
}
