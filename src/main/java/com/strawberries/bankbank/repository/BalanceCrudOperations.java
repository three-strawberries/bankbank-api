package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.entity.Balance;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BalanceCrudOperations implements CrudOperations<Balance> {


  @Override
  public List<Balance> findAll() {
    return null;
  }

  @Override
  public Balance save(Balance toSave) throws IllegalAccessException {
    return null;
  }

  @Override
  public boolean update(Balance entity) throws IllegalAccessException {
    return false;
  }
}
