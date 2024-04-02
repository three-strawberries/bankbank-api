package com.strawberries.bankbank.repository;

import java.util.List;

public interface CrudOperations<T> {
  List<T> findAll();

  T save(T toSave) throws IllegalAccessException;

  boolean update(T entity) throws IllegalAccessException;
}
