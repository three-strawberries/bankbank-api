package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.Transaction;
import com.strawberries.bankbank.repository.TransactionCrudOperations;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionServices {
  private final TransactionCrudOperations transactionCrudOperations;

  public TransactionServices(TransactionCrudOperations transactionCrudOperations)
      throws SQLException {
    this.transactionCrudOperations = transactionCrudOperations;
  }

  public List<Transaction> getAllTransactions() throws SQLException {
    return transactionCrudOperations.findAll();
  }

  public Transaction saveTransaction(Transaction transaction) throws SQLException, IllegalAccessException {
    return transactionCrudOperations.save(transaction);
  }

  public boolean updateTransaction(int idTransaction, Transaction transaction) throws SQLException, IllegalAccessException {
    return transactionCrudOperations.update(transaction);
  }
}
