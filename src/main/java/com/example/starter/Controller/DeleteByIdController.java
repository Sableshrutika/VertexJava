package com.example.starter.Controller;

import com.example.starter.model.Entites.Employee;
import com.example.starter.model.mapper.Response;
import com.example.starter.model.repos.EmployeRepo;
import com.example.starter.utils.ResponseUtils;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.ext.web.RoutingContext;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Optional;


public enum DeleteByIdController implements CommonController {
  INSTANCE;
  public static final Logger logger = LoggerFactory.getLogger(DeleteByIdController.class);

  @Override
  public void handle(RoutingContext context) {
    Response response = new Response();

    try {
      Integer employeeId = Integer.valueOf(context.pathParam("employeeId"));
      Optional<Employee> employee = Optional.ofNullable(EmployeRepo.INSTANCE.findById(employeeId));
      if (employee.isPresent()) {
        employee.get().delete();
        response.setMessage("Employee Detailes Delete successfully !!");
        ResponseUtils.INSTANCE.writeJsonResponse(context, response);
      } else {
        response.setErrors(Collections.singletonList("Employee Id Not Found "));
        ResponseUtils.INSTANCE.writeJsonErrorResponse(context,response, HttpResponseStatus.NOT_FOUND.code());

      }}
    catch (Exception e){
     logger.error("error ocucured :{}",e);
     response.setErrors(Collections.singletonList(e.getMessage()));
     ResponseUtils.INSTANCE.writeJsonErrorResponse(context,response,500);

    }
  }
}
