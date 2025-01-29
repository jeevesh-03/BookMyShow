package com.sampleProject.bookMyShowApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
//    @JoinColumn(name = "users_id")
//    @JsonBackReference("abc")
    private Users users;

    @ManyToOne
//    @JoinColumn(name = "show_id")
//    @JsonBackReference("def")
    private Show show;
    private LocalDateTime bookingDate;
    private Integer ticketCount;

    public Transaction(){

    }

    public void setUser(Users users) {
        this.users = users;
    }

    public Long getTransactionId() {
        return id;
    }

    public void setTransactionId(Long transactionId) {
        this.id = transactionId;
    }

    public Users getUser() {
        return users;
    }

    public Show getShowDetails() {
        return show;
    }

    public void setShowDetails(Show show) {
        this.show = show;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }
}
