package com.sampleProject.bookMyShowApp.services;

import com.sampleProject.bookMyShowApp.entities.Theater;
import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.response.TheaterResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TheaterService {

    void saveTheater(Theater t);

    List<TheaterResponse> getAllTheaters();

    Theater findTheaterByName(String name);

    Theater findTheaterById(Long id);

    TheaterResponse createTheater(String name, String city) throws WrongArgumentException;

    List<ShowResponse> getShowsOfTheater(String theaterName) throws NotFoundException;

    int getRevenue(Long theaterId) throws NotFoundException;

    List<TheaterResponse> getTheaterByCity(String cityName);
}
