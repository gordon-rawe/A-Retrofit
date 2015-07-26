package com.example.gordon.a_retrofit;

/**
 * Created by gordon on 2015/7/26.
 */
public class LocationEntity {
    private String cityName;
    private String placeName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Override
    public String toString() {
        return getCityName() + " " + getPlaceName();
    }
}
