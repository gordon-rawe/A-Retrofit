package com.example.gordon.a_retrofit.response;

import java.util.ArrayList;

/**
 * Created by gordon on 2015/7/25.
 */
public class CityResonseModel {

    @Override
    public String toString() {
        String ret = "";
        for(String place : cities){
            ret += place + "\n";
        }
        return ret;
    }

    private ArrayList<String> cities = new ArrayList<>();

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

}
