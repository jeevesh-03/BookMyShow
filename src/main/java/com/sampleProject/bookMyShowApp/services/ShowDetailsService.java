package com.sampleProject.bookMyShowApp.services;

import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.response.ShowResponse;

import java.util.List;


public interface ShowDetailsService {

    void saveShowDetails(Show s);

    List<ShowResponse> getAllShows();

    Show findShowById(Long showId);

    List<Show> findShowsByCity(String city);

    ShowResponse createShow(Long movieId, Long theaterId, Integer price, Integer capacity, String dateTime);

    int getRevenue(Long showId);
}
