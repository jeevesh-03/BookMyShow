package com.sampleProject.bookMyShowApp.controllers;

import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
import com.sampleProject.bookMyShowApp.response.TransactionResponse;
import com.sampleProject.bookMyShowApp.services.TransactionService;
import com.sampleProject.bookMyShowApp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;

    @GetMapping("/getAll")
    public List<TransactionResponse> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/create")
    public TransactionResponse createTransaction(@RequestParam("userId") Long userId,
                                                 @RequestParam("showId") Long showId,
                                                 @RequestParam("ticketCount") Integer ticketCount)
            throws WrongArgumentException, NotFoundException {
        return transactionService.createTransaction(userId, showId, ticketCount);
    }

    @PutMapping("money/add")
    public boolean addMoney(@RequestParam("userId") Long userId,
                            @RequestParam("amount") Integer amount)
            throws NotFoundException, WrongArgumentException {
        return transactionService.addMoney(userId, amount);
    }

    @GetMapping("money/view")
    public Long viewBalance(@RequestParam("userId") Long userId)
            throws NotFoundException {
        return userService.viewBalance(userId);
    }

    @GetMapping("/past")
    public List<TransactionResponse> viewPastBookings(@RequestParam("userId") Long userId)
            throws NotFoundException {
        return transactionService.getPreviousBookings(userId);
    }

    @GetMapping("/upcoming")
    public List<TransactionResponse> viewUpcomingBookings(@RequestParam("userId") Long userId)
            throws NotFoundException {
        return transactionService.getUpcomingBookings(userId);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelBooking(@RequestParam("userId") Long userId,
                                                @RequestParam("showId") Long showId) {
        transactionService.cancelBooking(userId, showId);
        return new ResponseEntity<>("Your booking has been cancelled", HttpStatus.OK);
    }
}