package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.Account;
import com.strawberries.bankbank.repository.AccountCrudOperations;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountServices {
  private final AccountCrudOperations accountCrudOrepations;

  public AccountServices(AccountCrudOperations accountCrudOrepations) throws SQLException {
    this.accountCrudOrepations = accountCrudOrepations;
  }

  public List<Account> getAllAccounts() throws SQLException {
    return accountCrudOrepations.findAll();
  }

  public Account saveAccount(Account account) throws SQLException {
    return accountCrudOrepations.save(account);
  }

  public boolean updateAccount(int idAccount, Account account) throws SQLException {
    return accountCrudOrepations.update(account);
  }
}
