package com.sampleProject.bookMyShowApp.services;


import com.sampleProject.bookMyShowApp.entities.Transaction;
import com.sampleProject.bookMyShowApp.response.TransactionResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


public interface TransactionService {

    @Transactional
    void saveTransaction(Transaction t);

    String createTransaction(Long userId, Long showId, Integer ticketCount) throws IllegalArgumentException, RuntimeException;

    List<TransactionResponse> getAllTransactions();

    List<TransactionResponse> getPreviousBookings(Long userId);

    List<TransactionResponse> getUpcomingBookings(Long userId);

    boolean addMoney(Long userId, Integer amount);
}
