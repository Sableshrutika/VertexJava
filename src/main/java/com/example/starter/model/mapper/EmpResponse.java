package com.example.starter.model.mapper;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class EmpResponse {

  @Id
private Integer employeeId;
private String employeeName;
private String employeeEmail;
private Integer employeeAge;
private double employeeSalary;
private Long createdAt;
private Long updatedAt;

  public EmpResponse(Integer employeeId, String employeeName, String employeeEmail, Integer employeeAge, double employeeSalary, Long createdAt, Long updatedAt) {
    this.employeeId = employeeId;
    this.employeeName = employeeName;
    this.employeeEmail = employeeEmail;
    this.employeeAge = employeeAge;
    this.employeeSalary = employeeSalary;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }


  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getEmployeeEmail() {
    return employeeEmail;
  }

  public void setEmployeeEmail(String employeeEmail) {
    this.employeeEmail = employeeEmail;
  }

  public Integer getEmployeeAge() {
    return employeeAge;
  }

  public void setEmployeeAge(Integer employeeAge) {
    this.employeeAge = employeeAge;
  }

  public double getEmployeeSalary() {
    return employeeSalary;
  }

  public void setEmployeeSalary(double employeeSalary) {
    this.employeeSalary = employeeSalary;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public Long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Long updatedAt) {
    this.updatedAt = updatedAt;
  }
}
