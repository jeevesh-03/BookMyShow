package com.sampleProject.bookMyShowApp.services.Impl;

import com.sampleProject.bookMyShowApp.entities.Movie;
import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.entities.Transaction;
import com.sampleProject.bookMyShowApp.helper.MovieToResponse;
import com.sampleProject.bookMyShowApp.helper.ShowToResponse;
import com.sampleProject.bookMyShowApp.repositories.MovieRepository;
import com.sampleProject.bookMyShowApp.repositories.ShowDetailsRepository;
import com.sampleProject.bookMyShowApp.response.MovieResponse;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImplementation implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowDetailsRepository showDetailsRepository;

    @Override
    public MovieResponse saveMovie(String name, boolean ageRestricted){
        Movie m=new Movie(name,ageRestricted);
        movieRepository.save(m);
        return MovieToResponse.convertEntity(m);
    }

    @Override
    public List<MovieResponse> getAllMovies(){
        return MovieToResponse.convertList(movieRepository.findAll());
    }

    @Override
    public Movie findMovieByName(String name){
        return movieRepository.findMovieByName(name);
    }

    @Override
    public Movie findMovieById(Long id){
        return movieRepository.findMovieById(id);
    }

    @Override
    public List<ShowResponse> findShowsByMovie(String movieName) {
        List<Show> shows= showDetailsRepository.findShowsByMovie(movieName);
        return ShowToResponse.convertList(shows);
    }

    @Override
    public int getRevenueOfMovie(String movieName){
        Movie m=findMovieByName(movieName);
        List<Show> shows=m.getShows();
        int revenue=0;
        for(Show sh: shows){
            List<Transaction> allTransactions=sh.getTransactions();
            for(Transaction transaction: allTransactions){
                revenue+=transaction.getTicketCount()*sh.getPrice();
            }
        }
        return revenue;
    }

    @Override
    public MovieResponse getMovie(String name, Long id){
        if(name==null && id==null) return null;
        if(name==null){
            Movie m=movieRepository.findMovieById(id);
            return MovieToResponse.convertEntity(m);
        }
        if(id==null){
            Movie m=movieRepository.findMovieByName(name);
            return MovieToResponse.convertEntity(m);
        }
        if(movieRepository.findMovieById(id)!=movieRepository.findMovieByName(name)) return null;
        return MovieToResponse.convertEntity(movieRepository.findMovieByName(name));
    }

}
