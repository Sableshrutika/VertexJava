package com.example.starter.External;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public enum ApiClientManager  {
  INSTANCE;
  private static final Logger logger= LoggerFactory.getLogger("ApiClientManger");
  public <T> T getApiClient(String baseUrl, Class<T> tClass) {
    Retrofit retrofit=null;
    HttpLoggingInterceptor httpLogging=new HttpLoggingInterceptor();
    httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

    try{
     retrofit=new Retrofit.Builder()
       .baseUrl(baseUrl)
       .client(new OkHttpClient().newBuilder()
       .addInterceptor(httpLogging).build())
       .addConverterFactory(GsonConverterFactory.create()).build();
    }
    catch (Exception e){
     logger.error(e.getMessage());
    }
    return  retrofit.create(tClass);
  }
}
