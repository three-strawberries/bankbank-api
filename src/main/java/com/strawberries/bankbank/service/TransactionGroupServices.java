package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.TransactionGroup;
import com.strawberries.bankbank.repository.TransactionGroupCrudOperations;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionGroupServices {
  private final TransactionGroupCrudOperations transactionGroupCrudOperations;
  public TransactionGroupServices(TransactionGroupCrudOperations transactionGroupCrudOperations) {
    this.transactionGroupCrudOperations = transactionGroupCrudOperations;
  }
  public List<TransactionGroup> getAllTransactionGroups()  {
    return transactionGroupCrudOperations.findAll();
  }
  public TransactionGroup saveTransactionGroup(TransactionGroup transaction) {
    return transactionGroupCrudOperations.save(transaction);
  }
  public boolean updateTransactionGroup(int idTransactionGroup, TransactionGroup transaction) {
    return transactionGroupCrudOperations.update(transaction);
  }
}
