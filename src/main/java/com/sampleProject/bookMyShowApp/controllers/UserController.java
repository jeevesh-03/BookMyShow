package com.sampleProject.bookMyShowApp.controllers;

import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.entities.Users;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.response.UserResponse;
import com.sampleProject.bookMyShowApp.services.ShowDetailsService;
import com.sampleProject.bookMyShowApp.services.TransactionService;
import com.sampleProject.bookMyShowApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ShowDetailsService showDetailsService;

    @GetMapping("/getAllUsers")
    public List<UserResponse> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("getUser")
    public UserResponse getUser(@RequestParam(value="name", required=false) String name, @RequestParam(value="id", required=false) Long id){
        return userService.getUser(name,id);
    }

    @PostMapping("/createUser")
    public UserResponse createUser(@RequestParam("name") String name, @RequestParam("age") Integer age, @RequestParam("city") String city, @RequestParam(value="walletBalance", required=false) Long walletBalance){
        return userService.createUser(name,age,city,walletBalance);
    }

    @GetMapping("shows")
    public List<ShowResponse> getShows(@RequestParam("userId") Long userId){
        return userService.getShows(userId);
    }

}
