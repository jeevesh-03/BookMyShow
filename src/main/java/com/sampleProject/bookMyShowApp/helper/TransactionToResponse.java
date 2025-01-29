package com.sampleProject.bookMyShowApp.helper;

import com.sampleProject.bookMyShowApp.entities.Transaction;
import com.sampleProject.bookMyShowApp.response.TransactionResponse;

import java.util.ArrayList;
import java.util.List;

public class TransactionToResponse {
    public static List<TransactionResponse> convertList(List<Transaction> allTransactions){
        List<TransactionResponse> allTransactionResponses= new ArrayList<>();

        for(Transaction t:allTransactions){
            int amount=t.getTicketCount()*t.getShowDetails().getPrice();
            allTransactionResponses.add(new TransactionResponse(t.getUser().getName(),t.getShowDetails().getMovie().getName(),t.getShowDetails().getTheater().getName(),t.getShowDetails().getTheater().getCity(),t.getShowDetails().getDateTime(),t.getTicketCount(),amount));
        }
        return allTransactionResponses;
    }

    public static TransactionResponse convertEntity(Transaction t){
        int amount=t.getTicketCount()*t.getShowDetails().getPrice();
        return new TransactionResponse(t.getUser().getName(),t.getShowDetails().getMovie().getName(),t.getShowDetails().getTheater().getName(),t.getShowDetails().getMovie().getName(),t.getShowDetails().getDateTime(),t.getTicketCount(),amount);
    }
}
