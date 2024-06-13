package com.example.starter.Controller;

import com.example.starter.model.Entites.Employee;
import com.example.starter.model.mapper.EmpResponse;
import com.example.starter.model.mapper.EmployeeMapper;
import com.example.starter.model.mapper.Response;
import com.example.starter.utils.ResponseUtils;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public enum AddEmployee implements CommonController {
  INSTANCE;
  private static final Logger logger = LoggerFactory.getLogger(AddEmployee.class);

  @Override
  public void handle(RoutingContext context) {

    Response response=new Response();
    try{
    JsonObject employeeJson= context.getBodyAsJson();
    logger.info("print some msg:",employeeJson.encodePrettily());
    Employee employee= employeeJson.mapTo(Employee.class);
    employee.save();
    EmpResponse empResponse= EmployeeMapper.INSTANCE.createEmployeeResponse(employee);
    response.setData(empResponse);
    response.setMessage("employee data succeefully saved!!");
    ResponseUtils.INSTANCE.writeJsonResponse(context,response,"successs");
    logger.info("employee Response:{}",JsonObject.mapFrom(response).encodePrettily());
  }
    catch(Exception e){
      response.setErrors(Arrays.asList(e.getMessage()));
      ResponseUtils.INSTANCE.writeJsonErrorResponse(context,response,500);
      logger.error("Error : {}", e.getMessage());

    }
  }

  @Override
  public void fail(String msg) {
    CommonController.super.fail(msg);
  }
}
