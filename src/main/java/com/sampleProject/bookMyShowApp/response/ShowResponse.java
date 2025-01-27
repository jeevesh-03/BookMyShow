package com.sampleProject.bookMyShowApp.response;

import java.time.LocalDateTime;

public class ShowResponse {
    String movieName;
    String theaterName;
    LocalDateTime showDateTime;

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

    public ShowResponse(String movieName, String theaterName, LocalDateTime showDateTime) {
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.showDateTime = showDateTime;
    }
}
