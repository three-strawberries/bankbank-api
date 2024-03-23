package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.TransactionGroup;
import com.strawberries.bankbank.repository.TransactionGroupCrudOperations;

import java.sql.SQLException;
import java.util.List;

public class TransactionGroupServices {
    private final TransactionGroupCrudOperations transactionGroupCrudOperations;

    public TransactionGroupServices(TransactionGroupCrudOperations transactionGroupCrudOperations) throws SQLException {
        this.transactionGroupCrudOperations = transactionGroupCrudOperations;
    }
    public List<TransactionGroup> getAllTransactionGroups() throws SQLException {
        return transactionGroupCrudOperations.findAll();
    }
    public TransactionGroup saveTransactionGroup(TransactionGroup transaction) throws SQLException {
        return transactionGroupCrudOperations.save(transaction);
    }
    public boolean updateTransactionGroup(int idTransactionGroup, TransactionGroup transaction) throws SQLException {
        return transactionGroupCrudOperations.update(transaction);
    }
}
