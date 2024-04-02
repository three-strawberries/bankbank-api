package com.strawberries.bankbank.controller;

import com.strawberries.bankbank.entity.Transaction;
import com.strawberries.bankbank.service.TransactionServices;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {
  private final TransactionServices transactionServices;

  public TransactionController(TransactionServices transactionServices) {
    this.transactionServices = transactionServices;
  }

  @GetMapping("/transactions")
  public List<Transaction> getAllTransactions() {
    return transactionServices.getAllTransactions();
  }

  @PostMapping("/transaction")
  public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
    Transaction savedTransaction = transactionServices.saveTransaction(transaction);
    return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
  }

  @PutMapping("/transaction/{idTransaction}")
  public ResponseEntity<Void> updateTransaction(@PathVariable int idTransaction, @RequestBody Transaction transactionUpdate) {
    boolean updateSuccess = transactionServices.updateTransaction(idTransaction, transactionUpdate);
    if (updateSuccess) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
