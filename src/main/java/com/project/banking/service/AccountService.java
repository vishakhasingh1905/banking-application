package com.project.banking.service;

import java.util.List;

import com.project.banking.dto.AccountDto;
import com.project.banking.dto.TransactionDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    void transferFunds(Long fromAccountId, Long toAccountId, double amount);
    void saveTransaction(TransactionDto transactionDto);
    List<TransactionDto> getTransactionHistory(Long accountId);
    void lockAccount(Long accountId);
    void unlockAccount(Long accountId);
}
