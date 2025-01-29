package com.sampleProject.bookMyShowApp.services.Impl;


import com.sampleProject.bookMyShowApp.entities.Users;
import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
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

    public Long viewBalance(Long userId) throws NotFoundException{
        try {
            Users u=findUserById(userId);
            if(u==null){
                throw new NotFoundException("User not found");
            }
            return u.getWalletBalance();
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating the transaction.");
        }
    }

    public UserResponse createUser(String name, Integer age, String city, Long walletBalance) throws WrongArgumentException {
        try {
            if(walletBalance==null){
                walletBalance=0L;
            }
            if(name==null || age<0 || city==null || walletBalance<0){
                throw new WrongArgumentException("Wrong input!");
            }
            Users u=new Users(name,age,city,walletBalance);
            saveUser(u);
            return UserToResponse.convertEntity(u);
        }
        catch (WrongArgumentException e) {
            throw new WrongArgumentException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating the transaction.");
        }
    }

    public UserResponse getUser(String name, Long id) throws NotFoundException{
        try {
            if(name==null && id==null){
                throw new NotFoundException("Empty name and id");
            }
            if(name==null){
                Users u=userRepository.findUserById(id);
                if(u==null){
                    throw new NotFoundException("No user exists with given id");
                }
                return UserToResponse.convertEntity(u);
            }
            if(id==null){
                Users u=userRepository.findUserByName(name);
                if(u==null){
                    throw new NotFoundException("No user exists with given name");
                }
                return UserToResponse.convertEntity(u);
            }
            if(userRepository.findUserById(id)!=userRepository.findUserByName(name)){
                throw new NotFoundException("No user exists with given name and id");
            }
            return UserToResponse.convertEntity(userRepository.findUserById(id));
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating the transaction.");
        }
    }

    public List<ShowResponse> getShows(Long userId) throws NotFoundException{
        try {
            Users u=findUserById(userId);
            if(u==null){
                throw new NotFoundException("User does not exist with given id!");
            }
            String city=u.getCity();
            return ShowToResponse.convertList(showDetailsService.findShowsByCity(city));
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating the transaction.");
        }
    }

    public UserResponse updateUser(Long id, Integer age, String city, String name) throws NotFoundException,WrongArgumentException{
        try {
            Users u=userRepository.findUserById(id);
            if(u==null){
                throw new NotFoundException("No user exists with given id");
            }
            if(age<0 || city==null || name==null){
                throw new WrongArgumentException("Wrong input!");
            }
            u.setAge(age);
            u.setCity(city);
            u.setName(name);
            userRepository.save(u);
            return UserToResponse.convertEntity(u);
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch(WrongArgumentException e){
            throw new WrongArgumentException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating the transaction.");
        }
    }
}
