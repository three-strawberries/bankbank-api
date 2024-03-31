package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.entity.Balance;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceCrudOperations extends AutoCrudOperations<Balance> {

  public BalanceCrudOperations(DataSource dataSource) {
    super(dataSource);
  }
}
