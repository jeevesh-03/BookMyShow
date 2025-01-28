package com.sampleProject.bookMyShowApp.controllers;


import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
import com.sampleProject.bookMyShowApp.response.ShowResponse;
import com.sampleProject.bookMyShowApp.response.UserResponse;
import com.sampleProject.bookMyShowApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<UserResponse> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/getByIdOrName")
    public UserResponse getUser(@RequestParam(value="name", required=false) String name,
                                @RequestParam(value="id", required=false) Long id)
            throws NotFoundException{
        return userService.getUser(name,id);
    }

    @PostMapping("/create")
    public UserResponse createUser(@RequestParam("name") String name,
                                   @RequestParam("age") Integer age,
                                   @RequestParam("city") String city,
                                   @RequestParam(value="walletBalance", required=false) Long walletBalance)
            throws WrongArgumentException {
        return userService.createUser(name,age,city,walletBalance);
    }

    @GetMapping("/shows")
    public List<ShowResponse> getShows(@RequestParam("userId") Long userId)
            throws NotFoundException{
        return userService.getShows(userId);
    }

    @PutMapping("/update")
    public UserResponse updateUser(@RequestParam("id") Long id,
                                   @RequestParam("age") Integer age,
                                   @RequestParam("city") String city,
                                   @RequestParam("name") String name)
            throws NotFoundException,WrongArgumentException{
        return userService.updateUser(id,age,city,name);
    }
}
