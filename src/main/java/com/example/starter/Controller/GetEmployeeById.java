package com.example.starter.Controller;

import com.example.starter.model.Entites.Employee;
import com.example.starter.model.mapper.Response;
import com.example.starter.model.repos.EmployeRepo;
import com.example.starter.utils.ResponseUtils;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.ext.web.RoutingContext;

import java.util.Collections;
import java.util.Optional;

public enum GetEmployeeById implements CommonController{
  Instance;

  @Override
  public void handle(RoutingContext context) {
  Integer employeeId=Integer.valueOf(context.pathParam("employeeId"));
  Optional<Employee>employee= Optional.ofNullable(EmployeRepo.INSTANCE.findById(employeeId));
    Response response=new Response();
    if(employee.isPresent()){
      response.setData(employee.get());
      response.setMessage("employeee details featch successfully !!!");
      ResponseUtils.INSTANCE.writeJsonResponse(context,response,"success");

    }
    else{
       response.setMessage("employee not found with given employeeId"+employeeId);
       response.setErrors(Collections.singletonList("employee Not found "));
       ResponseUtils.INSTANCE.writeJsonErrorResponse(context,response, HttpResponseStatus.NOT_FOUND.code());
    }
  }

  @Override
  public void fail(String msg) {
    CommonController.super.fail(msg);
  }
}
