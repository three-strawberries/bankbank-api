package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.Transaction;
import com.strawberries.bankbank.repository.TransactionCrudOperations;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class TransactionServices {
    private final TransactionCrudOperations transactionCrudOperations;

    public TransactionServices(TransactionCrudOperations transactionCrudOperations) throws SQLException {
        this.transactionCrudOperations = transactionCrudOperations;
    }
    public List<Transaction> getAllTransactions() throws SQLException {
        return transactionCrudOperations.findAll();
    }
    public Transaction saveTransaction(Transaction transaction) throws SQLException {
        return transactionCrudOperations.save(transaction);
    }
    public boolean updateTransaction(int idTransaction, Transaction transaction) throws SQLException {
        return transactionCrudOperations.update(transaction);
    }
}
