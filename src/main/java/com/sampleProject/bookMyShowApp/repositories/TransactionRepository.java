package com.sampleProject.bookMyShowApp.repositories;

import com.sampleProject.bookMyShowApp.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
