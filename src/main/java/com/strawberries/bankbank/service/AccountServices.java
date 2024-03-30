package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.Account;
import com.strawberries.bankbank.repository.AccountCrudOrepations;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountServices {
  private final AccountCrudOrepations accountCrudOrepations;

  public AccountServices(AccountCrudOrepations accountCrudOrepations) throws SQLException {
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
