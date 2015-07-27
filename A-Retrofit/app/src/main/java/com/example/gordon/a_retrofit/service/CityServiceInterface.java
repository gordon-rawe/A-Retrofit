package com.example.gordon.a_retrofit.service;

import com.example.gordon.a_retrofit.response.CityResonseModel;
import com.example.gordon.a_retrofit.response.CountyResponseModel;
import com.example.gordon.a_retrofit.response.ProvinceResponseModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by gordon on 2015/7/25.
 */
public interface CityServiceInterface {
    @GET("/provinces")
    void getProvinces(Callback<ProvinceResponseModel> callback);

    @GET("/cities/{province}")
    void getCities(@Path("province") String province,Callback<CityResonseModel> callback);

    @GET("/counties/{province}/{city}")
    void getCounties(@Path("province") String province,@Path("city") String city,Callback<CountyResponseModel> callback);
}
