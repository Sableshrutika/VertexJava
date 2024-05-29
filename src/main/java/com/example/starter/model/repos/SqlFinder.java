package com.example.starter.model.repos;

import com.example.starter.Factory.SqlBeanFactory;
import io.ebean.Database;
import io.ebean.ExpressionList;
import io.ebean.Query;

import java.util.List;

public class SqlFinder <T,I>{

  private Class<T> tClass;
  public SqlFinder(Class<T> tClass) {
    this.tClass = tClass;
  }

  private Database database() {
    return SqlBeanFactory.INSTANCE.dbConnection();

  }

  public T findById(I id) {
    return database().find(tClass, id);
  }

  public Query<T> query() {
    return database().find(tClass);
  }

  public List<T> findAll() {
    return query().findList();
  }

  public ExpressionList<T> getExpressionList() {
    return database().find(tClass).where();
  }
}
