package com.sampleProject.bookMyShowApp.repositories;

import com.sampleProject.bookMyShowApp.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Movie findMovieByName(String name);
    Movie findMovieById(Long id);
}
