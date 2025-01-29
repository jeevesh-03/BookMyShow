package com.sampleProject.bookMyShowApp.controllers;

import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
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

    @GetMapping("getAll")
    public List<TheaterResponse> getAll(){
        return theaterService.getAllTheaters();
    }

    @PostMapping("create")
    public TheaterResponse createTheater(@RequestParam("name") String name,
                                         @RequestParam("city") String city)
            throws WrongArgumentException {
        return theaterService.createTheater(name,city);
    }

    @GetMapping("/shows")
    public List<ShowResponse> getShows(@RequestParam("theaterId") Long theaterId) throws NotFoundException {
        return theaterService.getShowsOfTheater(theaterId);
    }

    @GetMapping("/revenue")
    public int getRevenueOfTheater(@RequestParam("theaterId") Long theaterId) throws NotFoundException{
        return theaterService.getRevenue(theaterId);
    }

    @GetMapping("/city")
    public List<TheaterResponse> getTheatersByCity(@RequestParam("cityName") String cityName){
        return theaterService.getTheaterByCity(cityName);
    }
}
