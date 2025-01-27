package com.sampleProject.bookMyShowApp.controllers;


import com.sampleProject.bookMyShowApp.repositories.MovieRepository;
import com.sampleProject.bookMyShowApp.repositories.TheaterRepository;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.services.MovieService;
import com.sampleProject.bookMyShowApp.services.ShowDetailsService;
import com.sampleProject.bookMyShowApp.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowDetailsService showDetailsService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private TheaterService theaterService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @GetMapping("getAllShows")
    public List<ShowResponse> getAllShows() {
        return showDetailsService.getAllShows();
    }

    @PostMapping("createShow")
    @Transactional
    public ShowResponse createShow(@RequestParam("movieId") Long movieId,
                           @RequestParam("theaterId") Long theaterId,
                           @RequestParam("price") Integer price,
                           @RequestParam("capacity") Integer capacity,
                           @RequestParam(value = "dateTime", required = false) String dateTime) {

        return showDetailsService.createShow(movieId,theaterId,price,capacity,dateTime);
    }

    @GetMapping("revenue")
    public int getShowRevenue(@RequestParam("showId") Long showId){
        return showDetailsService.getRevenue(showId);
    }
}
