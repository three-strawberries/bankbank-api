package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.Balance;
import com.strawberries.bankbank.repository.BalanceCrudOperations;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BalanceServices {
  private final BalanceCrudOperations balanceCrudOperations;

  public BalanceServices(BalanceCrudOperations balanceCrudOperations) {
    this.balanceCrudOperations = balanceCrudOperations;
  }
  public List<Balance> getAllBalances() {
    return balanceCrudOperations.findAll();
  }
  public Balance saveBalance(Balance balance) {
    return balanceCrudOperations.save(balance);
  }
  public boolean updateBalance(int idBalance, Balance balance) {
    return balanceCrudOperations.update(balance);
  }
}
