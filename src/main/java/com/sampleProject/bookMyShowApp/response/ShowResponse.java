package com.sampleProject.bookMyShowApp.response;

import java.time.LocalDateTime;

public class ShowResponse {
    String movieName;
    String theaterName;
    LocalDateTime showDateTime;
    String city;
    Long id;
    Integer capacity;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public LocalDateTime getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }

    public ShowResponse(String movieName, String theaterName, LocalDateTime showDateTime, String city, Long id, Integer capacity) {
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.showDateTime = showDateTime;
        this.city=city;
        this.id=id;
        this.capacity=capacity;
    }
}
