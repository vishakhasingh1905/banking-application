package com.project.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.banking.dto.AccountDto;
import com.project.banking.dto.TransactionDto;
import com.project.banking.dto.TransferRequestDto;
import com.project.banking.service.AccountService;
@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/addAccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAccount/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }
    @PutMapping("/deposit/{id}/{amount}")
    public ResponseEntity<AccountDto> deposit(@PathVariable String id, @PathVariable double amount){
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public ResponseEntity<AccountDto> withdraw(@PathVariable String id, @PathVariable double amount){
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransferRequestDto transferRequest) {
        accountService.transferFunds(transferRequest.getFromAccountId(),
                transferRequest.getToAccountId(),
                transferRequest.getAmount());
        return ResponseEntity.ok("Transfer successful");
    }
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactionHistory(@PathVariable String accountId) {
        List<TransactionDto> transactionHistory = accountService.getTransactionHistory(accountId);
        return ResponseEntity.ok(transactionHistory);
    }

    @PutMapping("/lock/{accountId}")
    public ResponseEntity<String> lockAccount(@PathVariable String accountId) {
        accountService.lockAccount(accountId);
        return ResponseEntity.ok("Account locked successfully");
    }

    @PutMapping("/unlock/{accountId}")
    public ResponseEntity<String> unlockAccount(@PathVariable String accountId) {
        accountService.unlockAccount(accountId);
        return ResponseEntity.ok("Account unlocked successfully");
    }


}
