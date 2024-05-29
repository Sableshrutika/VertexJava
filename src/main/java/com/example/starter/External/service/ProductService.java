package com.example.starter.External.service;

import com.example.starter.External.response.Product;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ProductService {

  @GET("/products")
  public Call<List<Product>> getAllProduct();
}
