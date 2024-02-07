package com.rancard.model.dto.transaction;

import lombok.Data;

import java.util.Optional;

@Data
public class UpdateTransactionDto {
    private Optional<Long> senderId;
    private Optional<Long> receiverId;
    private Optional<Double> amount;
}
