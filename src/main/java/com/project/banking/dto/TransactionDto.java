package com.project.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Long id;
    private Long accountId;
    private String transactionType;
    private double amount;
    private LocalDateTime timestamp;
    private String description;
}
