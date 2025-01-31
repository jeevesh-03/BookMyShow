package com.sampleProject.bookMyShowApp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean ageRestricted;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
//    @JsonManagedReference("show-movie")
//    @JsonIgnore
    private List<Show> shows;

    public Movie(String name, boolean ageRestricted) {
        this.name = name;
        this.ageRestricted = ageRestricted;
    }

    public Movie() {

    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public Long getMovieId() {
        return id;
    }

    public void setMovieId(Long movieId) {
        this.id = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAgeRestricted() {
        return ageRestricted;
    }

    public void setAgeRestricted(boolean ageRestricted) {
        this.ageRestricted = ageRestricted;
    }
}
