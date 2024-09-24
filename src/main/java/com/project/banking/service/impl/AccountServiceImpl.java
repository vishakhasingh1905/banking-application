package com.project.banking.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.project.banking.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.banking.dto.AccountDto;
import com.project.banking.dto.TransactionDto;
import com.project.banking.entity.Account;
import com.project.banking.entity.AccountStatus;
import com.project.banking.entity.Transaction;
import com.project.banking.mapper.AccountMapper;
import com.project.banking.mapper.TransactionMapper;
import com.project.banking.repository.AccountRepository;
import com.project.banking.repository.TransactionRepository;
import com.project.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        long generatedId = sequenceGeneratorService.generateSequence("account_sequence");
        Account account = AccountMapper.mapToAccount(accountDto);
        account.setId(generatedId);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(String id) {
        long accountId = Long.parseLong(id);
        Account account = accountRepository
                .findById(accountId).orElseThrow(
                        () -> new RuntimeException("Account does not exist")
                );

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(String id, double amount) {
        long accountId = Long.parseLong(id);
        Account account = accountRepository
                .findById(accountId).orElseThrow(
                        () -> new RuntimeException("Account does not exist")
                );

        if (account.getStatus() == AccountStatus.LOCKED) {
            throw new RuntimeException("Account is locked. Cannot perform transactions.");
        }

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        // Save the deposit transaction
        TransactionDto transaction = new TransactionDto();
        transaction.setAccountId(accountId);
        transaction.setTransactionType("DEPOSIT");
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Deposit to account");
        saveTransaction(transaction);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(String id, double amount) {
        long accountId = Long.parseLong(id);
        Account account = accountRepository
                .findById(accountId).orElseThrow(
                        () -> new RuntimeException("Account does not exist")
                );

        if (account.getStatus() == AccountStatus.LOCKED) {
            throw new RuntimeException("Account is locked. Cannot perform transactions.");
        }

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        Account updatedAccount = accountRepository.save(account);

        // Save the withdrawal transaction
        TransactionDto transaction = new TransactionDto();
        transaction.setAccountId(accountId);
        transaction.setTransactionType("WITHDRAWAL");
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Withdrawal from account");
        saveTransaction(transaction);

        return AccountMapper.mapToAccountDto(updatedAccount);
    }

    @Override
    public void transferFunds(String fromAccountId, String toAccountId, double amount) {
        long fromId = Long.parseLong(fromAccountId);
        long toId = Long.parseLong(toAccountId);
        Account fromAccount = accountRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Source account does not exist"));
        Account toAccount = accountRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Destination account does not exist"));

        if (fromAccount.getStatus() == AccountStatus.LOCKED) {
            throw new RuntimeException("Source account is locked. Cannot perform transactions.");
        }

        if (toAccount.getStatus() == AccountStatus.LOCKED) {
            throw new RuntimeException("Destination account is locked. Cannot perform transactions.");
        }

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds in the source account");
        }

        // Deduct from the source account
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.save(fromAccount);

        // Save the debit transaction for the source account
        TransactionDto debitTransaction = new TransactionDto();
        debitTransaction.setAccountId(fromId);
        debitTransaction.setTransactionType("TRANSFER_OUT");
        debitTransaction.setAmount(amount);
        debitTransaction.setTimestamp(LocalDateTime.now());
        debitTransaction.setDescription("Transfer to account " + toAccountId);
        saveTransaction(debitTransaction);

        // Add to the destination account
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(toAccount);

        // Save the credit transaction for the destination account
        TransactionDto creditTransaction = new TransactionDto();
        creditTransaction.setAccountId(toId);
        creditTransaction.setTransactionType("TRANSFER_IN");
        creditTransaction.setAmount(amount);
        creditTransaction.setTimestamp(LocalDateTime.now());
        creditTransaction.setDescription("Transfer from account " + fromAccountId);
        saveTransaction(creditTransaction);
    }

    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = TransactionMapper.mapToTransaction(transactionDto);
        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDto> getTransactionHistory(String accountId) {
        long id = Long.parseLong(accountId);
        List<Transaction> transactions = transactionRepository.findByAccountId(id);
        return transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .toList();
    }

    @Override
    public void lockAccount(String accountId) {
        long id = Long.parseLong(accountId);
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        account.setStatus(AccountStatus.LOCKED);
        accountRepository.save(account);
    }

    @Override
    public void unlockAccount(String accountId) {
        long id = Long.parseLong(accountId);
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        account.setStatus(AccountStatus.ACTIVE);
        accountRepository.save(account);
    }
}
