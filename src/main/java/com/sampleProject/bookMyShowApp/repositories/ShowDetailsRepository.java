package com.sampleProject.bookMyShowApp.repositories;

import com.sampleProject.bookMyShowApp.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowDetailsRepository extends JpaRepository<Show,Long> {
    Show findShowById(Long showId);

    @Query("select s from Show s join s.theater t where t.city= :city")
    List<Show> findShowByCity(String city);

    @Query("select s from Show s join s.movie m where m.name= :movieName")
    List<Show> findShowsByMovie(String movieName);
}
