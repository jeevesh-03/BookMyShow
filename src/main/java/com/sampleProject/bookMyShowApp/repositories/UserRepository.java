package com.sampleProject.bookMyShowApp.repositories;

import com.sampleProject.bookMyShowApp.entities.Transaction;
import com.sampleProject.bookMyShowApp.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Long> {
    Users findUserByName(String name);

    Users findUserById(Long id);

}
