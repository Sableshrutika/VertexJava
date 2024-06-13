package com.example.starter.External;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sun.util.resources.cldr.ar.CalendarData_ar_LB;

public interface GenderizeService {

  @GET("/")
Call<PredicateGenderResponse> getGenderFromName(@Query("name") String name);

}
