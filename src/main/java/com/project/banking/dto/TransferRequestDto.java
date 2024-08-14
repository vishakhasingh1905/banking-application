package com.project.banking.dto;

import lombok.Data;

@Data
public class TransferRequestDto {
    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
}
