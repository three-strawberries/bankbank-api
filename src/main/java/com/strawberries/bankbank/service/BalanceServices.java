package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.Balance;
import com.strawberries.bankbank.repository.BalanceCrudOperations;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BalanceServices {
  private final BalanceCrudOperations balanceCrudOperations;

  public BalanceServices(BalanceCrudOperations balanceCrudOperations) throws SQLException {
    this.balanceCrudOperations = balanceCrudOperations;
  }

  public List<Balance> getAllBalances() throws SQLException {
    return balanceCrudOperations.findAll();
  }

  public Balance saveBalance(Balance balance) throws SQLException, IllegalAccessException {
    return balanceCrudOperations.save(balance);
  }

  public boolean updateBalance(int idBalance, Balance balance) throws SQLException, IllegalAccessException {
    return balanceCrudOperations.update(balance);
  }
}
