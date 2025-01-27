package com.sampleProject.bookMyShowApp.repositories;

import com.sampleProject.bookMyShowApp.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater,Long> {
    Theater getTheaterByName(String name);

    Theater getTheaterById(Long id);
}
