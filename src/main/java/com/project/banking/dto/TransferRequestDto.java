package com.project.banking.dto;

import lombok.Data;

@Data
public class TransferRequestDto {
    private String fromAccountId;
    private String toAccountId;
    private double amount;
}
