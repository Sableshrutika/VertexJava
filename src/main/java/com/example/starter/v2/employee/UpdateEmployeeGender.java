package com.example.starter.v2.employee;

import com.example.starter.Controller.CommonController;
import com.example.starter.External.GenderizeService;
import com.example.starter.External.PredicateGenderResponse;
import com.example.starter.exception.RoutingError;
import com.example.starter.model.Entites.Employee;
import com.example.starter.model.mapper.Response;
import com.example.starter.model.repos.EmployeRepo;
import com.example.starter.utils.ResponseUtils;
import com.example.starter.v2.external.GenderPredicateService;
import io.reactivex.rxjava3.core.Single;
import io.vertx.ext.web.RoutingContext;

public enum UpdateEmployeeGender implements CommonController {
  INSTANCE;

  @Override
  public void handle(RoutingContext context) {
    Single.just(context)
      .map(this::updateGender)
      .subscribe(
        response -> ResponseUtils.INSTANCE.writeJsonResponse(context, response),
        error -> ResponseUtils.INSTANCE.handleError(context, error)
      );
  }


  private Response updateGender(RoutingContext context){
    Response response=new Response();

    try{
    Integer employeeID1=Integer.valueOf(context.request().getParam("id"));
    Integer employeeId2=Integer.valueOf(context.request().getParam("id"));
    Employee employee= EmployeRepo.INSTANCE.findById(employeeID1);

    if(employee==null){
      throw new RoutingError("Employee Not found");
    }
    PredicateGenderResponse genderName= GenderPredicateService.INSTANCE.getGenderByName(employee.getEmployeeName());
    employee.setGender(genderName.getGender());
    employee.save();
    response.setMessage("success");



    }
    catch (Exception e){
      System.out.println(e);
      throw new RoutingError(e.getMessage());
    }
   return  response;
  }
}
