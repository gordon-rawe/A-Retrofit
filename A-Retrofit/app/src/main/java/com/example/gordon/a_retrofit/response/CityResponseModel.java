package com.example.gordon.a_retrofit.response;

import java.util.ArrayList;

/**
 * Created by gordon on 7/24/15.
 */
public class CityResponseModel {
    private ArrayList<String> cities;

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        String ret = "";
        for(String str:cities){
            ret += str+"\n";
        }
        return ret;
    }
}