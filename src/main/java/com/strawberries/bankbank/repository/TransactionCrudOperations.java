package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.entity.Transaction;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionCrudOperations implements CrudOperations<Transaction> {


  @Override
  public List<Transaction> findAll() {
    return null;
  }

  @Override
  public Transaction save(Transaction toSave) throws IllegalAccessException {
    return null;
  }

  @Override
  public boolean update(Transaction entity) throws IllegalAccessException {
    return false;
  }
}
