package com.example.starter.v2.external;

import com.example.starter.Controller.CommonController;
import com.example.starter.External.PredicateGenderResponse;
import com.example.starter.exception.RoutingError;
import com.example.starter.model.mapper.Response;
import com.example.starter.utils.ResponseUtils;
import io.reactivex.rxjava3.core.Single;
import io.vertx.ext.web.RoutingContext;

public enum PredicateGenderController implements CommonController {
  INSTANCE ;

  @Override
  public void handle(RoutingContext context) {
    Single.just(context)
      .map(this::doOnNext)
      .subscribe(
        response -> ResponseUtils.INSTANCE.writeJsonResponse(context, response),
        error -> ResponseUtils.INSTANCE.handleError(context, error)
      );
  }


    private Response doOnNext(RoutingContext context){
      Response response=new Response();
      try{
      String name=context.request().getParam("name");
      PredicateGenderResponse genderByName=GenderPredicateService.INSTANCE.getGenderByName(name);
      response.setMessage("success");
      response.setData(genderByName);
      }
      catch (Exception e){
         System.out.println(e);
         throw new RoutingError(e.getMessage());
      }
      return  response;
     }
  }
