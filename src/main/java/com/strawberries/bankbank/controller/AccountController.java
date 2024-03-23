package com.strawberries.bankbank.controller;

import com.strawberries.bankbank.entity.Account;
import com.strawberries.bankbank.service.AccountServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class  AccountController {
    private final AccountServices accountServices;

    public AccountController(AccountServices accountServices) {
        this.accountServices = accountServices;
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() throws SQLException {
        return accountServices.getAllAccounts();
    }

    @PostMapping("/account")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) throws SQLException {
        Account savedAccount = accountServices.saveAccount(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }
    @PutMapping("/account/{idAccount}")
    public ResponseEntity<Void> updateAccount(@PathVariable int idAccount, @RequestBody Account accountUpdate) throws SQLException {
        boolean updateSuccess = accountServices.updateAccount(idAccount, accountUpdate);
        if (updateSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


