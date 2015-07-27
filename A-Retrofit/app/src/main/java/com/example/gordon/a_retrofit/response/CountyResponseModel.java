package com.example.gordon.a_retrofit.response;

import java.util.ArrayList;

/**
 * Created by gordon on 7/24/15.
 */
public class CountyResponseModel {
    private ArrayList<String> counties;

    public ArrayList<String> getCounties() {
        return counties;
    }

    public void setCounties(ArrayList<String> counties) {
        this.counties = counties;
    }

    @Override
    public String toString() {
        String ret = "";
        for(String str: counties){
            ret += str+"\n";
        }
        return ret;
    }
}