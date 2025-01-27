package com.sampleProject.bookMyShowApp.services.Impl;

import com.sampleProject.bookMyShowApp.entities.Movie;
import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.entities.Theater;
import com.sampleProject.bookMyShowApp.entities.Transaction;
import com.sampleProject.bookMyShowApp.helper.ShowToResponse;
import com.sampleProject.bookMyShowApp.repositories.MovieRepository;
import com.sampleProject.bookMyShowApp.repositories.ShowDetailsRepository;
import com.sampleProject.bookMyShowApp.repositories.TheaterRepository;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.services.MovieService;
import com.sampleProject.bookMyShowApp.services.ShowDetailsService;
import com.sampleProject.bookMyShowApp.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShowDetailsServiceImplementation implements ShowDetailsService {
    @Autowired
    private ShowDetailsRepository showDetailsRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TheaterService theaterService;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;


    @Override
    public void saveShowDetails(Show s){
        showDetailsRepository.save(s);
    }

    @Override
    public List<ShowResponse> getAllShows(){
        List<Show> shows= showDetailsRepository.findAll();
        return ShowToResponse.convertList(shows);
    }

    @Override
    public Show findShowById(Long showId){
        return showDetailsRepository.findShowById(showId);
    }

    @Override
    public List<Show> findShowsByCity(String city){
        return showDetailsRepository.findShowByCity(city);
    }

    @Override
    public ShowResponse createShow(Long movieId, Long theaterId, Integer price, Integer capacity, String dateTime){
        Movie m = movieService.findMovieById(movieId);
        Theater t = theaterService.findTheaterById(theaterId);

        if (m == null || t == null) {
            throw new IllegalArgumentException("Movie or Theater not found with given IDs.");
        }

        Show s = new Show();
        s.setMovie(m);
        s.setTheater(t);
        s.setPrice(price);
        s.setCapacity(capacity);

        if (dateTime != null) {
            s.setDateTime(LocalDateTime.parse(dateTime));
        } else {
            s.setDateTime(LocalDateTime.now());
        }

        saveShowDetails(s);
        m.getShows().add(s);
        t.getShows().add(s);

        movieRepository.save(m);
        theaterRepository.save(t);

        return ShowToResponse.convertEntity(s);
    }

    @Override
    public int getRevenue(Long showId){
        Show s=findShowById(showId);
        int revenue=0;
        List<Transaction> allTransactions=s.getTransactions();
        for(Transaction t: allTransactions){
            revenue+=t.getTicketCount()*s.getPrice();
        }
        return revenue;
    }
}
