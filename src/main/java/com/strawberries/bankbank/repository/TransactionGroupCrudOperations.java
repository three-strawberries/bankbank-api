package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.entity.TransactionGroup;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionGroupCrudOperations implements CrudOperations<TransactionGroup> {


  @Override
  public List<TransactionGroup> findAll() {
    return null;
  }

  @Override
  public TransactionGroup save(TransactionGroup toSave) throws IllegalAccessException {
    return null;
  }

  @Override
  public boolean update(TransactionGroup entity) throws IllegalAccessException {
    return false;
  }
}
