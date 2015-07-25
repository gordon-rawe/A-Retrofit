package com.example.gordon.a_retrofit.service;

import com.example.gordon.a_retrofit.response.CityResponseModel;
import com.example.gordon.a_retrofit.response.PlaceResonseModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by gordon on 2015/7/25.
 */
public interface CityServiceInterface {
    @GET("/cities")
    void getCities(Callback<CityResponseModel> callback);

    @GET("/cities/{city}")
    void getCityPlaces(@Path("city") String city,Callback<PlaceResonseModel> callback);
}
