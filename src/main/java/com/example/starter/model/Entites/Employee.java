package com.example.starter.model.Entites;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;


@Entity
@Table(name="Emps")
public class Employee extends BaseModel{

  private String employeeName;
  private String employeeEmail;
  private  Integer employeeAge;
  private double empSalary;
  private String gender;

  public Employee(@NonNull Integer Id, Timestamp createdAt, Timestamp updatedAt, boolean deleted, String employeeName, String employeeEmail, Integer employeeAge, double empSalary,String gender) {
    super(Id, createdAt, updatedAt, deleted);
    this.employeeName = employeeName;
    this.employeeEmail = employeeEmail;
    this.employeeAge = employeeAge;
    this.empSalary = empSalary;
    this.gender=gender;
  }

  public Employee(@NonNull Integer Id, Timestamp createdAt, Timestamp updatedAt, boolean deleted) {
    super(Id, createdAt, updatedAt, deleted);
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

  public double getEmpSalary() {
    return empSalary;
  }

  public void setEmpSalary(double empSalary) {
    this.empSalary = empSalary;
  }

  public void setGender(String empGender){this.gender=gender;}

  public String getGender(){return gender;}
}
