package com.projects.solutionpack.model.currentweathermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CurrentWeatherResponse {

    @SerializedName("location")
    @Expose
    private Location location;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @SerializedName("current")
    @Expose
    private Current current;

}
