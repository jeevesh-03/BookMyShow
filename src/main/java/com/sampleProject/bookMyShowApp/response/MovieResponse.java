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

    String movieName;
}
