package com.project.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private String id;
    private long accountId;
    private String transactionType;
    private double amount;
    private LocalDateTime timestamp;
    private String description;
}
