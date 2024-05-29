package com.example.starter.Controller;

import com.example.starter.model.Entites.Employee;
import com.example.starter.model.mapper.EmpResponse;
import com.example.starter.model.mapper.EmployeeMapper;
import com.example.starter.model.mapper.Response;
import com.example.starter.model.repos.EmployeRepo;
import com.example.starter.utils.ResponseUtils;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GetAllEmployeController implements CommonController {
  INSTANCE;
  private static final Logger logger = LoggerFactory.getLogger(GetAllEmployeController.class);

  @Override
  public void handle(RoutingContext context) {
    Response response=new Response();

    try{
    List<Employee> employee= EmployeRepo.INSTANCE.findAll();
    if(employee==null||employee.isEmpty()){
    ResponseUtils.INSTANCE.writeJsonErrorResponse(context,response, HttpResponseStatus.NOT_FOUND.code());
  }

  else{
  List<EmpResponse> empResponses=employee.stream()
    .map(EmployeeMapper.INSTANCE::createEmployeeResponse)
    .collect(Collectors.toList());
     response.setData(empResponses);
     response.setMessage("data featch successfully !!!");
     logger.info("Response ,Employee:{}", JsonObject.mapFrom(response).encodePrettily());
     ResponseUtils.INSTANCE.writeJsonResponse(context,response,"success");}
    }
   catch(Exception e){
//   logger.error("error in featching records :{}" e);
     System.out.println("error in featching record ");
    response.setErrors(Arrays.asList(e.getMessage()));
    ResponseUtils.INSTANCE.writeJsonErrorResponse(context,response,500);
    }

  }

  @Override
  public void fail(String msg) {
    CommonController.super.fail(msg);
  }
}
