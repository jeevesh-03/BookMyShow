package com.sampleProject.bookMyShowApp.services.Impl;

import com.sampleProject.bookMyShowApp.entities.Movie;
import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.entities.Transaction;
import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
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
    public MovieResponse saveMovie(String name, boolean ageRestricted) throws WrongArgumentException{
        try {
            if(name==null){
                throw new WrongArgumentException("Movie name is empty!");
            }
            Movie m=new Movie(name,ageRestricted);
            movieRepository.save(m);
            return MovieToResponse.convertEntity(m);
        } catch (WrongArgumentException e) {
            throw new WrongArgumentException(e.getMessage());
        }
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
    public List<ShowResponse> findShowsByMovie(String movieName) throws NotFoundException{
        try {
            if(movieRepository.findMovieByName(movieName)==null){
                throw new NotFoundException("Movie not found");
            }
            List<Show> shows= showDetailsRepository.findShowsByMovie(movieName);
            return ShowToResponse.convertList(shows);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public int getRevenueOfMovie(String movieName) throws NotFoundException{
        try {
            if(movieRepository.findMovieByName(movieName)==null){
                throw new NotFoundException("Movie not found");
            }
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
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public MovieResponse getMovie(String name, Long id) throws WrongArgumentException,NotFoundException{
        try {
            if(name==null && id==null){
                throw new WrongArgumentException("Name and Id provided is null");
            }
            if(name==null){
                Movie m=movieRepository.findMovieById(id);
                if(m==null){
                    throw new NotFoundException("No movie exists with given id");
                }
                return MovieToResponse.convertEntity(m);
            }
            if(id==null){
                Movie m=movieRepository.findMovieByName(name);
                if(m==null){
                    throw new NotFoundException("No movie exists with given name");
                }
                return MovieToResponse.convertEntity(m);
            }
            if(movieRepository.findMovieById(id)!=movieRepository.findMovieByName(name)){
                throw new NotFoundException("No movie exists with given name and id");
            }
            return MovieToResponse.convertEntity(movieRepository.findMovieByName(name));
        } catch (WrongArgumentException e) {
            throw new WrongArgumentException(e.getMessage());
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

}
