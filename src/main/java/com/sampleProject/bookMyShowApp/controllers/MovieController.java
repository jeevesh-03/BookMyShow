package com.sampleProject.bookMyShowApp.controllers;

import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
import com.sampleProject.bookMyShowApp.response.MovieResponse;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("getAll")
    public Mono<List<MovieResponse>> getAll() {
        return movieService.getAllMovies();
    }

    @PostMapping("create")
    public Mono<MovieResponse> createMovie(@RequestParam("name") String name,
                                           @RequestParam("ageRestricted") boolean ageRestricted)
            throws WrongArgumentException {
        return movieService.saveMovie(name, ageRestricted);
    }

    @GetMapping("getByIdOrName")
    public Mono<MovieResponse> getMovie(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "id", required = false) Long id)
            throws WrongArgumentException, NotFoundException {
        return movieService.getMovie(name, id);
    }

    @GetMapping("shows")
    public Mono<List<ShowResponse>> getShowsByMovie(@RequestParam("movieName") String movieName)
            throws NotFoundException {
        return movieService.findShowsByMovie(movieName);
    }

    @GetMapping("revenue")
    public Mono<Integer> getRevenueOfMovie(@RequestParam("movieName") String movieName)
            throws NotFoundException {
        return movieService.getRevenueOfMovie(movieName);
    }
}
