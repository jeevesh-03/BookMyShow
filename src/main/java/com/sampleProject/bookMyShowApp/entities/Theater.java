package com.sampleProject.bookMyShowApp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String name;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
//    @JsonManagedReference("show-theater")
//    @JsonIgnore
    private List<Show> shows;

    public Theater(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public Theater() {

    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public Long getTheaterId() {
        return id;
    }

    public void setTheaterId(Long theaterId) {
        this.id = theaterId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
