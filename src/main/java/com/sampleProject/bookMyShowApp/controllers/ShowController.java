package com.sampleProject.bookMyShowApp.controllers;


import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.services.ShowDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/show")
@AllArgsConstructor
public class ShowController {

    private final ShowDetailsService showDetailsService;

    @GetMapping("getAll")
    public List<ShowResponse> getAllShows() {
        return showDetailsService.getAllShows();
    }

    @PostMapping("create")
    @Transactional
    public ShowResponse createShow(@RequestParam("movieId") Long movieId,
                                   @RequestParam("theaterId") Long theaterId,
                                   @RequestParam("price") Integer price,
                                   @RequestParam("capacity") Integer capacity,
                                   @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime)
            throws WrongArgumentException, NotFoundException {

        return showDetailsService.createShow(movieId, theaterId, price, capacity, dateTime);
    }

    @GetMapping("revenue")
    public int getShowRevenue(@RequestParam("showId") Long showId)
            throws NotFoundException {
        return showDetailsService.getRevenue(showId);
    }
}
