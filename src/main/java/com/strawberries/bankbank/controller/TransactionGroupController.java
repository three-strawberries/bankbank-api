package com.strawberries.bankbank.controller;

import com.strawberries.bankbank.entity.TransactionGroup;
import com.strawberries.bankbank.service.TransactionGroupServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class  TransactionGroupController {
    private final TransactionGroupServices transactionGroupServices;

    public TransactionGroupController(TransactionGroupServices transactionGroupServices) {
        this.transactionGroupServices = transactionGroupServices;
    }

    @GetMapping("/transactionGroups")
    public List<TransactionGroup> getAllTransactionGroups() throws SQLException {
        return transactionGroupServices.getAllTransactionGroups();
    }

    @PostMapping("/transactionGroup")
    public ResponseEntity<TransactionGroup> addTransactionGroup(@RequestBody TransactionGroup transactionGroup) throws SQLException {
        TransactionGroup savedTransactionGroup = transactionGroupServices.saveTransactionGroup(transactionGroup);
        return new ResponseEntity<>(savedTransactionGroup, HttpStatus.CREATED);
    }
    @PutMapping("/transactionGroup/{idTransactionGroup}")
    public ResponseEntity<Void> updateTransactionGroup(@PathVariable int idTransactionGroup, @RequestBody TransactionGroup transactionGroupUpdate) throws SQLException {
        boolean updateSuccess = transactionGroupServices.updateTransactionGroup(idTransactionGroup, transactionGroupUpdate);
        if (updateSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}





