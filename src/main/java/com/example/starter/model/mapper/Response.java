package com.example.starter.model.mapper;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//@Data
public class Response {

  private Object data;
  private String message;
  private List<String> errors;

  public List<String>getError(){
  if(errors==null){
   errors=new ArrayList<>();
  }
  return errors;
  }

  public Response(){

  }

  public  Response(String message){
    this.getError().add(message);
    this.message=message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }
}
