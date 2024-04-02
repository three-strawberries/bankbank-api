package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.Transaction;
import com.strawberries.bankbank.repository.TransactionCrudOperations;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionServices {
  private final TransactionCrudOperations transactionCrudOperations;

  public TransactionServices(TransactionCrudOperations transactionCrudOperations){
    this.transactionCrudOperations = transactionCrudOperations;
  }

  public List<Transaction> getAllTransactions()  {
    return transactionCrudOperations.findAll();
  }

  public Transaction saveTransaction(Transaction transaction)  {
    return transactionCrudOperations.save(transaction);
  }

  public boolean updateTransaction(int idTransaction, Transaction transaction) {
    return transactionCrudOperations.update(transaction);
  }
}
