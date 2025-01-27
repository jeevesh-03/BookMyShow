package com.sampleProject.bookMyShowApp.services;

import com.sampleProject.bookMyShowApp.entities.Movie;
import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.response.MovieResponse;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface MovieService {

    MovieResponse saveMovie(String name, boolean ageRestricted);

    List<MovieResponse> getAllMovies();

    Movie findMovieByName(String name);

    Movie findMovieById(Long id);

    List<ShowResponse> findShowsByMovie(String movieName);

    int getRevenueOfMovie(String movieName);

    MovieResponse getMovie(String name, Long id);
}

