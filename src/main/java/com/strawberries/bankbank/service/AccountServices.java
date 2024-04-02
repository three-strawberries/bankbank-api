package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.Account;
import java.util.List;
import com.strawberries.bankbank.repository.AccountCrudOperations;
import org.springframework.stereotype.Service;

@Service
public class AccountServices {
  private final AccountCrudOperations accountCrudOrepations;

  public AccountServices(AccountCrudOperations accountCrudOrepations) {
    this.accountCrudOrepations = accountCrudOrepations;
  }

  public List<Account> getAllAccounts() {
    return accountCrudOrepations.findAll();
  }

  public Account saveAccount(Account account) {
    return accountCrudOrepations.save(account);
  }

  public boolean updateAccount(int idAccount, Account account) {
    return accountCrudOrepations.update(account);
  }
}
