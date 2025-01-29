package com.sampleProject.bookMyShowApp.response;

public class TheaterResponse {
    String theaterName;
    String city;
    Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TheaterResponse(String theaterName, String city, Long id) {
        this.theaterName = theaterName;
        this.city = city;
        this.id=id;
    }
}
