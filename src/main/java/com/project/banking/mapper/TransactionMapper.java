package com.project.banking.mapper;

import com.project.banking.dto.TransactionDto;
import com.project.banking.entity.Transaction;

public class TransactionMapper {

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getTimestamp(),
                transaction.getDescription()
        );
    }

    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(transactionDto.getAccountId());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTimestamp(transactionDto.getTimestamp());
        transaction.setDescription(transactionDto.getDescription());
        return transaction;
    }
}
