package com.example.starter.model.Entites;

import com.example.starter.Factory.SqlBeanFactory;
import io.ebean.annotation.SoftDelete;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

//@Data
@MappedSuperclass
public class BaseModel {
  @Id
  @NonNull
  @Column(length = 40)
  private Integer Id;

  @WhenCreated
  private Timestamp createdAt;
  @WhenModified
  private Timestamp updatedAt;
  @SoftDelete
  private boolean deleted;

  public BaseModel(@NonNull Integer Id, Timestamp createdAt, Timestamp updatedAt, boolean deleted) {
    this.Id = Id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deleted = deleted;
  }

  public @NonNull Integer getId() {
    return Id;
  }

  public void setId(@NonNull Integer Id) {
    this.Id = Id;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public void save(){
    if (createdAt==null) {
      SqlBeanFactory.INSTANCE.saveBean(this);
    } else {
      SqlBeanFactory.INSTANCE.update(this);
    }
  }

  public void delete() {
    SqlBeanFactory.INSTANCE.delete(this);
  }




}
