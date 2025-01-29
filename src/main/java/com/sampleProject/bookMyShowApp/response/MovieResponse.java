package com.sampleProject.bookMyShowApp.response;


public class MovieResponse {

    public MovieResponse(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public MovieResponse(String movieName, Long id, boolean ageRestricted) {
        this.movieName = movieName;
        this.id = id;
        this.ageRestricted = ageRestricted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAgeRestricted() {
        return ageRestricted;
    }

    public void setAgeRestricted(boolean ageRestricted) {
        this.ageRestricted = ageRestricted;
    }

    String movieName;
    Long id;
    boolean ageRestricted;
}
