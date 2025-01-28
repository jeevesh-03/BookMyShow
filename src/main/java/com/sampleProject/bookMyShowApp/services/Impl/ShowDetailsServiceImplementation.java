package com.sampleProject.bookMyShowApp.services.Impl;

import com.sampleProject.bookMyShowApp.entities.Movie;
import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.entities.Theater;
import com.sampleProject.bookMyShowApp.entities.Transaction;
import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
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
    public ShowResponse createShow(Long movieId, Long theaterId, Integer price, Integer capacity, LocalDateTime dateTime) throws WrongArgumentException,NotFoundException{
        try {
            if(movieId==null || theaterId==null || price==null || capacity==null){
                throw new WrongArgumentException("Wrong input!");
            }
            Movie m = movieService.findMovieById(movieId);
            Theater t = theaterService.findTheaterById(theaterId);

            if (m == null || t == null) {
                throw new NotFoundException("Movie or theater does not exist");
            }

            if(dateTime.isBefore(LocalDateTime.now())){
                throw new WrongArgumentException("Enter an upcoming show time");
            }

            Show s = new Show();
            s.setMovie(m);
            s.setTheater(t);
            s.setPrice(price);
            s.setCapacity(capacity);

            if (dateTime != null) {
                s.setDateTime(dateTime);
            } else {
                s.setDateTime(LocalDateTime.now());
            }

            saveShowDetails(s);
            m.getShows().add(s);
            t.getShows().add(s);

            movieRepository.save(m);
            theaterRepository.save(t);

            return ShowToResponse.convertEntity(s);
        } catch (WrongArgumentException e) {
            throw new WrongArgumentException(e.getMessage());
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public int getRevenue(Long showId) throws NotFoundException{
        try {
            Show s=findShowById(showId);
            if(s==null){
                throw new NotFoundException("Show not found");
            }
            int revenue=0;
            List<Transaction> allTransactions=s.getTransactions();
            for(Transaction t: allTransactions){
                revenue+=t.getTicketCount()*s.getPrice();
            }
            return revenue;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
