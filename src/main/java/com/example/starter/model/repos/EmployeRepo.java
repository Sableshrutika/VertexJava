package com.example.starter.model.repos;

import com.example.starter.model.Entites.Employee;
import io.ebean.ExpressionList;

import java.util.List;

public enum EmployeRepo {
  INSTANCE;
private SqlFinder<Employee,Integer>employeeFinder=new SqlFinder<>(Employee.class);



public Employee findById(Integer id){
 return employeeFinder.findById(id);
}

public List<Employee> findAll(){
  return employeeFinder.findAll();
}

public ExpressionList<Employee> FindAllEmployee(){
return employeeFinder.query().where();

}
}
