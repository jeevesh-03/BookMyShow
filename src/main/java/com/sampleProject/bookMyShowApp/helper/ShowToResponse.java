package com.sampleProject.bookMyShowApp.helper;

import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.response.ShowResponse;

import java.util.ArrayList;
import java.util.List;

public class ShowToResponse {
    public static List<ShowResponse> convertList(List<Show> allShows){
        List<ShowResponse> allShowResponses=new ArrayList<>();
        for(Show s: allShows){
            allShowResponses.add(new ShowResponse(s.getMovie().getName(),s.getTheater().getName(),s.getDateTime(),s.getTheater().getCity(),s.getShowId(),s.getCapacity()));
        }
        return allShowResponses;
    }
    public static ShowResponse convertEntity(Show s){
        return new ShowResponse(s.getMovie().getName(),s.getTheater().getName(),s.getDateTime(),s.getTheater().getCity(),s.getShowId(),s.getCapacity());
    }
}
