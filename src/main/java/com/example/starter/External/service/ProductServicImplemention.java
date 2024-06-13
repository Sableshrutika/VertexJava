package com.example.starter.External.service;

import com.example.starter.config.ConfigHelper;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public enum ProductServicImplemention {
INSTANCE;

 public ProductService FakeStoreApi(){
  String baseUrl= ConfigHelper.INSTANCE.getExternalConfig()
    .getJsonObject("products").getString("baseUrl");
  HttpLoggingInterceptor httpLogging=new HttpLoggingInterceptor();
  httpLogging.setLevel(HttpLoggingInterceptor.Level.BASIC);
   OkHttpClient client= new OkHttpClient
     .Builder()
     .addInterceptor(httpLogging)
     .build();

   Retrofit retrofit=new Retrofit.Builder()
     .baseUrl(baseUrl)
     .addConverterFactory(GsonConverterFactory.create())
     .client(client)
     .build();

   return retrofit.create(ProductService.class);

 }
}
