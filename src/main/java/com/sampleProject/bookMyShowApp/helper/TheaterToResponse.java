package com.sampleProject.bookMyShowApp.helper;

import com.sampleProject.bookMyShowApp.entities.Theater;
import com.sampleProject.bookMyShowApp.response.TheaterResponse;

import java.util.ArrayList;
import java.util.List;

public class TheaterToResponse {
    public static List<TheaterResponse> converList(List<Theater> allTheaters){
        List<TheaterResponse> allTheaterResponses=new ArrayList<>();
        for(Theater th: allTheaters){
            allTheaterResponses.add(new TheaterResponse(th.getName(), th.getCity(), th.getTheaterId()));
        }
        return allTheaterResponses;
    }

    public static TheaterResponse convertEntity(Theater th){
        return new TheaterResponse(th.getName(), th.getCity(),th.getTheaterId());
    }
}
