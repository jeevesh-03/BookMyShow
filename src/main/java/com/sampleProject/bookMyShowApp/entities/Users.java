package com.sampleProject.bookMyShowApp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String city;
    private Long walletBalance;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
//    @JsonManagedReference("abc")
//    @JsonIgnore
    private List<Transaction> bookings;

    public Users() {

    }

    public Users(String name, Integer age, String city, Long walletBalance) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.walletBalance = walletBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.id = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(Long walletBalance) {
        this.walletBalance = walletBalance;
    }

    public List<Transaction> getBookings() {
        return bookings;
    }

    public void setBookings(List<Transaction> bookings) {
        this.bookings = bookings;
    }
}
