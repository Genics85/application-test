package com.rancard.model.dto.transaction;

import lombok.Data;

@Data
public class CreateTransactionDto {
    private Long senderId;
    private Long receiverId;
    private Double amount;
}
