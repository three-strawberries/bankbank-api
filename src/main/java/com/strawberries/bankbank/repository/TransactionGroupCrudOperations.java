package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.entity.TransactionGroup;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionGroupCrudOperations extends AutoCrudOperations<TransactionGroup> {

  public TransactionGroupCrudOperations(DataSource dataSource) {
    super(dataSource);
  }
}
