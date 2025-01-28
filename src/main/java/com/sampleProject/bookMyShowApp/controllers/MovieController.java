package com.sampleProject.bookMyShowApp.controllers;

import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
import com.sampleProject.bookMyShowApp.response.MovieResponse;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("getAll")
    public List<MovieResponse> getAll(){
        return movieService.getAllMovies();
    }

    @PostMapping("create")
    public MovieResponse createMovie(@RequestParam("name") String name,
                                     @RequestParam("ageRestricted") boolean ageRestricted)
            throws WrongArgumentException {
        return movieService.saveMovie(name, ageRestricted);
    }

    @GetMapping("getByIdOrName")
    public MovieResponse getMovie(@RequestParam(value="name", required = false) String name,
                                  @RequestParam(value="id", required=false) Long id)
            throws WrongArgumentException, NotFoundException {
        return movieService.getMovie(name,id);
    }

    @GetMapping("shows")
    public List<ShowResponse> getShowsByMovie(@RequestParam("movieName") String movieName)
            throws NotFoundException{
        return movieService.findShowsByMovie(movieName);
    }

    @GetMapping("revenue")
    public int getRevenueOfMovie(@RequestParam("movieName") String movieName)
            throws NotFoundException{
        return movieService.getRevenueOfMovie(movieName);
    }
}
