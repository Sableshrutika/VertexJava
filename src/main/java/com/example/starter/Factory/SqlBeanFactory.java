package com.example.starter.Factory;

import com.example.starter.config.ConfigHelper;
import com.example.starter.model.Entites.BaseModel;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.config.dbplatform.mysql.MySqlPlatform;
import io.ebean.datasource.DataSourceConfig;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum SqlBeanFactory {
  INSTANCE;

  private static final Logger logger = LoggerFactory.getLogger(SqlBeanFactory.class);

  private DataSourceConfig getDataSourceConfig() {
    JsonObject jsonObject = ConfigHelper.INSTANCE.getMySqlConfig();
    logger.info("SqlConfig : {}", jsonObject.encodePrettily());
    DataSourceConfig config = new DataSourceConfig();
    config.setDriver(jsonObject.getString("driver"));
    config.setUrl(jsonObject.getString("url"));
    config.setUsername(jsonObject.getString("username"));
    config.setPassword(jsonObject.getString("password"));
    logger.info("DataSourceConfig : {}", JsonObject.mapFrom(config).encodePrettily());
    return config;
  }

  public Database dbConnection() {
    DatabaseConfig dbConfig = new DatabaseConfig();
    dbConfig.setDataSourceConfig(getDataSourceConfig());
    dbConfig.addPackage("com.example.starter.model.Entites");
//		dbConfig.addClass(Employee.class);

    dbConfig.setDdlGenerate(true);
    dbConfig.setDdlRun(true);
    dbConfig.setDefaultServer(true);
    dbConfig.setDdlCreateOnly(true);
    dbConfig.setRegister(true);
    dbConfig.setDatabasePlatform(new MySqlPlatform());
    return DatabaseFactory.create(dbConfig);
  }

  public void init() {
    dbConnection();
  }
  public void stop() {
    dbConnection().shutdown();
  }

  public void save(BaseModel baseModel) {
    saveBean(baseModel);
  }

  public void update(BaseModel baseModel) {
    updateBean(baseModel);
  }

  public void delete(BaseModel baseModel) {
    deleteBean(baseModel);
  }
  public void saveBean(Object o) {
    dbConnection().save(o);
  }

  public void updateBean(Object o) {
    dbConnection().update(o);
  }

  public void deleteBean(Object o) {
    dbConnection().delete(o);
  }
}
