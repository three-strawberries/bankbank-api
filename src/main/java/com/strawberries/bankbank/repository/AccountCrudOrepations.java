package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.entity.Account;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountCrudOrepations implements CrudOperations<Account> {

  @Override
  public List<Account> findAll() {
    return null;
  }

  @Override
  public Account save(Account toSave) throws IllegalAccessException {
    return null;
  }

  @Override
  public boolean update(Account entity) throws IllegalAccessException {
    return false;
  }
}
