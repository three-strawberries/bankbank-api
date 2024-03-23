package com.strawberries.bankbank.service;

import com.strawberries.bankbank.entity.Balance;
import com.strawberries.bankbank.repository.BalanceCrudOperations;

import java.sql.SQLException;
import java.util.List;

public class BalancesServices {
    private final BalanceCrudOperations balanceCrudOperations;

    public BalancesServices(BalanceCrudOperations balanceCrudOperations) throws SQLException {
        this.balanceCrudOperations = balanceCrudOperations;
    }
    public List<Balance> getAllBalances() throws SQLException {
        return balanceCrudOperations.findAll();
    }
    public Balance saveBalance(Balance balance) throws SQLException {
        return balanceCrudOperations.save(balance);
    }
    public boolean updateBalance(int idBalance, Balance balance) throws SQLException {
        return balanceCrudOperations.update(balance);
    }
}
