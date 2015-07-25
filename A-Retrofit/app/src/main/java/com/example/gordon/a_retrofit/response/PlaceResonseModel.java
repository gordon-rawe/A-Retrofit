package com.example.gordon.a_retrofit.response;

import java.util.ArrayList;

/**
 * Created by gordon on 2015/7/25.
 */
public class PlaceResonseModel {

    @Override
    public String toString() {
        String ret = "";
        for(Place place : places){
            ret += place.getName() + "\n";
        }
        return ret;
    }

    private ArrayList<Place> places = new ArrayList<>();

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public class  Place{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
