package com.sampleProject.bookMyShowApp.services.Impl;

import com.sampleProject.bookMyShowApp.entities.Show;
import com.sampleProject.bookMyShowApp.entities.Transaction;
import com.sampleProject.bookMyShowApp.entities.Users;
import com.sampleProject.bookMyShowApp.exceptions.NotFoundException;
import com.sampleProject.bookMyShowApp.exceptions.WrongArgumentException;
import com.sampleProject.bookMyShowApp.helper.TransactionToResponse;
import com.sampleProject.bookMyShowApp.repositories.TransactionRepository;
import com.sampleProject.bookMyShowApp.response.TransactionResponse;
import com.sampleProject.bookMyShowApp.services.ShowDetailsService;
import com.sampleProject.bookMyShowApp.services.TransactionService;
import com.sampleProject.bookMyShowApp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImplementation implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final ShowDetailsService showDetailsService;

    @Transactional
    @Override
    public void saveTransaction(Transaction t){
        Users u=t.getUser();
        Show s=t.getShowDetails();
        u.getBookings().add(t);
        s.getTransactions().add(t);
        u.setWalletBalance(u.getWalletBalance()-((long) s.getPrice() *t.getTicketCount()));
        s.setCapacity(s.getCapacity()-t.getTicketCount());
        transactionRepository.save(t);
        userService.saveUser(u);
        showDetailsService.saveShowDetails(s);
    }

    @Override
    public TransactionResponse createTransaction(Long userId, Long showId, Integer ticketCount) throws WrongArgumentException, NotFoundException{
        try {
            Users u = userService.findUserById(userId);
            Show s = showDetailsService.findShowById(showId);

            if (u == null || s == null) {
                throw new NotFoundException("Show or User not found with given IDs!");
            }

            if (ticketCount <= 0) {
                throw new WrongArgumentException("Please enter valid ticket count!");
            }

            if (s.getCapacity() < ticketCount) {
                throw new WrongArgumentException("Tickets Sold Out!");
            }

            if (s.getDateTime().getMonth() != LocalDateTime.now().getMonth() ||
                    s.getDateTime().getDayOfMonth() < LocalDateTime.now().getDayOfMonth()) {
                throw new WrongArgumentException("Movie show's date has already passed!");
            }

            int amount = ticketCount * s.getPrice();

            if (u.getWalletBalance() < amount) {
                throw new WrongArgumentException("Insufficient Balance!");
            }

            if (u.getAge() < 18 && s.getMovie().isAgeRestricted()) {
                throw new WrongArgumentException("You cannot book ticket as this movie is age restricted!");
            }

            List<Transaction> allTransactions=u.getBookings();
            boolean found=false;
            for(Transaction t:allTransactions){
                if(t.getShowDetails().getShowId().equals(s.getShowId()) && s.getDateTime().isAfter(LocalDateTime.now())){
                    found=true;
                    break;
                }
            }
            if(found) {
                throw new WrongArgumentException("Booking found for given user and show");
            }

            Transaction t = new Transaction();
            t.setShowDetails(s);
            t.setUser(u);
            t.setTicketCount(ticketCount);
            t.setBookingDate(LocalDateTime.now());
            saveTransaction(t);
            return TransactionToResponse.convertEntity(t);
        }
        catch (WrongArgumentException e) {
            throw new WrongArgumentException(e.getMessage());
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating the transaction.");
        }
    }

    @Override
    public List<TransactionResponse> getAllTransactions(){
        List<Transaction> all=transactionRepository.findAll();
        return TransactionToResponse.convertList(all);
    }

    @Override
    public List<TransactionResponse> getPreviousBookings(Long userId) throws NotFoundException{
        try {
            Users u=userService.findUserById(userId);
            if(u==null){
                throw new NotFoundException("User Not found");
            }
            List<Transaction> all=u.getBookings();
            LocalDateTime now=LocalDateTime.now();
            List<Transaction> previous=all.stream().filter(x -> (x.getShowDetails().getDateTime().isBefore(LocalDateTime.now()))).toList();
            return TransactionToResponse.convertList(previous);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred.");
        }
    }

    @Override
    public List<TransactionResponse> getUpcomingBookings(Long userId) throws NotFoundException{
        try {
            Users u=userService.findUserById(userId);
            if(u==null){
                throw new NotFoundException("User not found");
            }
            List<Transaction> all=u.getBookings();
            LocalDateTime now=LocalDateTime.now();
            List<Transaction> upcoming=all.stream().filter(x ->(x.getShowDetails().getDateTime().isAfter(LocalDateTime.now()))).toList();
            return TransactionToResponse.convertList(upcoming);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred.");
        }
    }

    @Override
    public boolean addMoney(Long userId, Integer amount) throws NotFoundException,WrongArgumentException{
        try {
            Users u=userService.findUserById(userId);
            if(u==null){
                throw new NotFoundException("User not found");
            }
            if(amount<=0){
                throw new WrongArgumentException("Please enter valid money amount");
            }
            u.setWalletBalance(u.getWalletBalance()+amount);
            userService.saveUser(u);
            return true;
        }
        catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        catch (WrongArgumentException e) {
            throw new WrongArgumentException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred.");
        }
    }
    @Transactional
    public void deleteTransaction(Users u, Show s, Transaction t){
        u.getBookings().remove(t);
        s.getTransactions().remove(t);
        u.setWalletBalance(u.getWalletBalance()+((long) t.getTicketCount() *t.getShowDetails().getPrice()));
        s.setCapacity(s.getCapacity()+t.getTicketCount());
        userService.saveUser(u);
        showDetailsService.saveShowDetails(s);
        transactionRepository.delete(t);
    }
    public void cancelBooking(Long userId, Long showId) throws NotFoundException{
        try {
            Users u=userService.findUserById(userId);
            Show s=showDetailsService.findShowById(showId);

            if(u==null){
                throw new NotFoundException("User not found");
            }
            if(s==null){
                throw new NotFoundException("Show not found");
            }
            List<Transaction> allTransactions=u.getBookings();
            boolean found=false;
            for(Transaction t:allTransactions){
                if(t.getShowDetails().getShowId().equals(s.getShowId()) && s.getDateTime().isAfter(LocalDateTime.now())){
                    found=true;
                    deleteTransaction(u,s,t);
                    break;
                }
            }
            if(!found) {
                throw new NotFoundException("No upcoming booking found with given user and show");
            }
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch(Exception e){
            throw new RuntimeException("Unexpected error");
        }
    }
}
