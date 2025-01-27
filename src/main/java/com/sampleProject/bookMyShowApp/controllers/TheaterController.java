package com.sampleProject.bookMyShowApp.controllers;

import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.response.TheaterResponse;
import com.sampleProject.bookMyShowApp.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @GetMapping("getAllTheaters")
    public List<TheaterResponse> getAll(){
        return theaterService.getAllTheaters();
    }

    @PostMapping("createTheater")
    public TheaterResponse createTheater(@RequestParam("name") String name, @RequestParam("city") String city){
        return theaterService.createTheater(name,city);
    }

    @GetMapping("/shows")
    public List<ShowResponse> getShows(@RequestParam("theaterName") String theaterName){
        return theaterService.getShowsOfTheater(theaterName);
    }

    @GetMapping("/revenue")
    public int getRevenueOfTheater(@RequestParam("theaterId") Long theaterId){
        return theaterService.getRevenue(theaterId);
    }

    @GetMapping("/city")
    public List<TheaterResponse> getTheatersByCity(@RequestParam("cityName") String cityName){
        return theaterService.getTheaterByCity(cityName);
    }
}
