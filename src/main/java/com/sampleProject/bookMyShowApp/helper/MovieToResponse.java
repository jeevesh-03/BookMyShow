package com.sampleProject.bookMyShowApp.helper;

import com.sampleProject.bookMyShowApp.entities.Movie;
import com.sampleProject.bookMyShowApp.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieToResponse {
    public static List<MovieResponse> convertList(List<Movie> all){
        List<MovieResponse> allMovies=new ArrayList<>();
        for(Movie m:all){
            MovieResponse mr=new MovieResponse(m.getName(),m.getMovieId(),m.isAgeRestricted());
            allMovies.add(mr);
        }
        return allMovies;
    }

    public static MovieResponse convertEntity(Movie m){
        return new MovieResponse(m.getName(),m.getMovieId(),m.isAgeRestricted());
    }
}
