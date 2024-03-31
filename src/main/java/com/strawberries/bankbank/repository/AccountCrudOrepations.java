package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.entity.Account;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountCrudOrepations extends AutoCrudOperations<Account> {
  @Autowired
  public AccountCrudOrepations(DataSource dataSource) {
    super(dataSource);
  }
}
