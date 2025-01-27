package com.sampleProject.bookMyShowApp.services;

import com.sampleProject.bookMyShowApp.entities.Users;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    void saveUser(Users u);

    List<UserResponse> getAllUsers();

    Users findUserByName(String name);

    Users findUserById(Long id);

    Long viewBalance(Long userId);

    UserResponse createUser(String name, Integer age, String city, Long walletBalance);

    UserResponse getUser(String name, Long id);

    List<ShowResponse> getShows(Long userId);
}
