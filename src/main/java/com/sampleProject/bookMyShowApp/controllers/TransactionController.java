package com.sampleProject.bookMyShowApp.controllers;

import com.sampleProject.bookMyShowApp.entities.Transaction;
import com.sampleProject.bookMyShowApp.response.TransactionResponse;
import com.sampleProject.bookMyShowApp.services.ShowDetailsService;
import com.sampleProject.bookMyShowApp.services.TransactionService;
import com.sampleProject.bookMyShowApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<TransactionResponse> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public String createTransaction(@RequestParam("userId") Long userId, @RequestParam("showId") Long showId,
                                        @RequestParam("ticketCount") Integer ticketCount) throws IllegalArgumentException, RuntimeException{
        return transactionService.createTransaction(userId,showId,ticketCount);
    }

    @PutMapping("money/add")
    public boolean addMoney(@RequestParam("userId") Long userId, @RequestParam("amount") Integer amount) throws RuntimeException{
        return transactionService.addMoney(userId,amount);
    }

    @GetMapping("money/view")
    public Long viewBalance(@RequestParam("userId") Long userId){
        return userService.viewBalance(userId);
    }

    @GetMapping("past")
    public List<TransactionResponse> viewPastBookings(@RequestParam("userId") Long userId){
        return transactionService.getPreviousBookings(userId);
    }

    @GetMapping("upcoming")
    public List<TransactionResponse> viewUpcomingBookings(@RequestParam("userId") Long userId){
        return transactionService.getUpcomingBookings(userId);
    }
}
