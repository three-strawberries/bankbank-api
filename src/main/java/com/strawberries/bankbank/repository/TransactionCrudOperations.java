package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.entity.Transaction;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionCrudOperations extends AutoCrudOperations<Transaction> {

  public TransactionCrudOperations(DataSource dataSource) {
    super(dataSource);
  }
}
