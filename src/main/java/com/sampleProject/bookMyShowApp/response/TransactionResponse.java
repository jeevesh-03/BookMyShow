package com.sampleProject.bookMyShowApp.response;

import java.time.LocalDateTime;


public class TransactionResponse {
    String userName;
    String movieName;
    String theaterName;
    String city;
    LocalDateTime showDate;
    Integer ticketCount;
    Integer totalAmount;
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDateTime showDate) {
        this.showDate = showDate;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public TransactionResponse(String userName, String movieName, String theaterName, String city, LocalDateTime showDate, Integer ticketCount, Integer totalAmount, Long id) {
        this.userName = userName;
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.city = city;
        this.showDate = showDate;
        this.ticketCount = ticketCount;
        this.totalAmount = totalAmount;
        this.id=id;
    }
}
