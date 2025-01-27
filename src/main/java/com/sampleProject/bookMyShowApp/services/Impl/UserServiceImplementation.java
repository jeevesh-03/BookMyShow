package com.sampleProject.bookMyShowApp.services.Impl;

import com.sampleProject.bookMyShowApp.entities.Movie;
import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.entities.Users;
import com.sampleProject.bookMyShowApp.helper.MovieToResponse;
import com.sampleProject.bookMyShowApp.helper.ShowToResponse;
import com.sampleProject.bookMyShowApp.helper.UserToResponse;
import com.sampleProject.bookMyShowApp.repositories.UserRepository;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.response.UserResponse;
import com.sampleProject.bookMyShowApp.services.ShowDetailsService;
import com.sampleProject.bookMyShowApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowDetailsService showDetailsService;

    @Override
    public void saveUser(Users u){
        userRepository.save(u);
    }

    @Override
    public List<UserResponse> getAllUsers(){
        return UserToResponse.convertList(userRepository.findAll());
    }

    @Override
    public Users findUserByName(String name){
        return userRepository.findUserByName(name);
    }

    @Override
    public Users findUserById(Long id){
        return userRepository.findUserById(id);
    }

    public Long viewBalance(Long userId){
        Users u=findUserById(userId);
        return u.getWalletBalance();
    }

    public UserResponse createUser(String name, Integer age, String city, Long walletBalance){
        Users u=new Users(name,age,city,walletBalance);
        saveUser(u);
        return UserToResponse.convertEntity(u);
    }

    public UserResponse getUser(String name, Long id){
        if(name==null && id==null) return null;
        if(name==null){
            Users u=userRepository.findUserById(id);
            return UserToResponse.convertEntity(u);
        }
        if(id==null){
            Users u=userRepository.findUserByName(name);
            return UserToResponse.convertEntity(u);
        }
        if(userRepository.findUserById(id)!=userRepository.findUserByName(name)) return null;
        return UserToResponse.convertEntity(userRepository.findUserById(id));
    }

    public List<ShowResponse> getShows(Long userId){
        Users u=findUserById(userId);
        String city=u.getCity();
        return ShowToResponse.convertList(showDetailsService.findShowsByCity(city));
    }

    public UserResponse updateUser(Long id, Integer age, String city, String name) throws RuntimeException{
        try {
            Users u=userRepository.findUserById(id);
            if(u==null){
                throw new RuntimeException("No user exists with id "+id.toString());
            }
            u.setAge(age);
            u.setCity(city);
            u.setName(name);
            userRepository.save(u);
            return UserToResponse.convertEntity(u);
        }
        catch (RuntimeException e) {
            System.out.println(e);
        }
        return null;
    }
}
