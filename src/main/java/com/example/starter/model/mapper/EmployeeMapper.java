package com.example.starter.model.mapper;


import ch.qos.logback.core.status.Status;
import com.example.starter.Controller.CommonController;
import com.example.starter.model.Entites.Employee;
import io.ebean.DB;
import io.ebean.Database;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.ext.web.RequestBody;
import io.vertx.ext.web.RoutingContext;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

import static java.time.ZoneId.systemDefault;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public enum EmployeeMapper {
  INSTANCE;

  public EmpResponse createEmployeeResponse(Employee employee) {
    return EmpResponse.builder()
      .employeeId(employee.getId())
      .employeeName(employee.getEmployeeName())
      .employeeEmail(employee.getEmployeeEmail())
      .employeeSalary(employee.getEmpSalary())
      .employeeAge(employee.getEmployeeAge())
      .createdAt(employee.getCreatedAt().getTime())
      .updatedAt(employee.getUpdatedAt().getTime())
      .build();
}}
