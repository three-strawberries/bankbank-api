package com.strawberries.bankbank.controller;

import com.strawberries.bankbank.entity.Balance;
import com.strawberries.bankbank.service.BalanceServices;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BalanceController {
  private final BalanceServices balanceServices;

  public BalanceController(BalanceServices balanceServices) {
    this.balanceServices = balanceServices;
  }

  @GetMapping("/balances")
  public List<Balance> getAllBalances() throws SQLException {
    return balanceServices.getAllBalances();
  }

  @PostMapping("/balance")
  public ResponseEntity<Balance> addBalance(@RequestBody Balance balance) throws SQLException {
    Balance savedBalance = balanceServices.saveBalance(balance);
    return new ResponseEntity<>(savedBalance, HttpStatus.CREATED);
  }

  @PutMapping("/balance/{idBalance}")
  public ResponseEntity<Void> updateBalance(
      @PathVariable int idBalance, @RequestBody Balance balanceUpdate) throws SQLException {
    boolean updateSuccess = balanceServices.updateBalance(idBalance, balanceUpdate);
    if (updateSuccess) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
