package com.sampleProject.bookMyShowApp.response;

public class TheaterResponse {
    String theaterName;
    String city;

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public TheaterResponse(String theaterName, String city) {
        this.theaterName = theaterName;
        this.city = city;
    }
}
