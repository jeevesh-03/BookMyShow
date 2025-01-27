package com.sampleProject.bookMyShowApp.services.Impl;

import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.entities.Theater;
import com.sampleProject.bookMyShowApp.entities.Transaction;
import com.sampleProject.bookMyShowApp.helper.ShowToResponse;
import com.sampleProject.bookMyShowApp.helper.TheaterToResponse;
import com.sampleProject.bookMyShowApp.repositories.TheaterRepository;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.response.TheaterResponse;
import com.sampleProject.bookMyShowApp.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServiceImplementation implements TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public void saveTheater(Theater t){
        theaterRepository.save(t);
    }

    @Override
    public List<TheaterResponse> getAllTheaters(){
        List<Theater> th= theaterRepository.findAll();
        return TheaterToResponse.converList(th);
    }

    @Override
    public Theater findTheaterByName(String name){
        return theaterRepository.getTheaterByName(name);
    }

    @Override
    public Theater findTheaterById(Long id){
        return theaterRepository.getTheaterById(id);
    }

    @Override
    public TheaterResponse createTheater(String name, String city){
        Theater t=new Theater(city,name);
        saveTheater(t);
        return TheaterToResponse.convertEntity(t);
    }

    @Override
    public List<ShowResponse> getShowsOfTheater(String theaterName){
        Theater t=findTheaterByName(theaterName);
        List<Show> allShows= t.getShows();
        return ShowToResponse.convertList(allShows);
    }

    @Override
    public int getRevenue(Long theaterId){
        Theater t=findTheaterById(theaterId);
        List<Show> shows=t.getShows();
        int revenue=0;
        for(Show sh: shows){
            List<Transaction> transactionList=sh.getTransactions();
            for(Transaction tr: transactionList){
                revenue+=tr.getTicketCount()*sh.getPrice();
            }
        }
        return revenue;
    }

    @Override
    public List<TheaterResponse> getTheaterByCity(String cityName){
        List<TheaterResponse> all=getAllTheaters();
        return all.stream().filter(x-> x.getCity().equals(cityName)).toList();
    }
}
