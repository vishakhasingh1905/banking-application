package com.project.banking.service;

import java.util.List;

import com.project.banking.dto.AccountDto;
import com.project.banking.dto.TransactionDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(String id);
    AccountDto deposit(String id, double amount);
    AccountDto withdraw(String id, double amount);
    void transferFunds(String fromAccountId, String toAccountId, double amount);
    void saveTransaction(TransactionDto transactionDto);
    List<TransactionDto> getTransactionHistory(String accountId);
    void lockAccount(String accountId);
    void unlockAccount(String accountId);
}
