package com.sampleProject.bookMyShowApp.services;

import com.sampleProject.bookMyShowApp.entities.Theater;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.response.TheaterResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TheaterService {

    void saveTheater(Theater t);

    List<TheaterResponse> getAllTheaters();

    Theater findTheaterByName(String name);

    Theater findTheaterById(Long id);

    TheaterResponse createTheater(String name, String city);

    List<ShowResponse> getShowsOfTheater(String theaterName);

    int getRevenue(Long theaterId);

    List<TheaterResponse> getTheaterByCity(String cityName);
}
