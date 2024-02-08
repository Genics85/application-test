package com.rancard.model.dto.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Optional;

@Data
public class UpdateTransactionDto {
    @NotNull(message = "Transaction id must be provided, type of Long")
    private Long id;
    private Optional<Long> senderId;
    private Optional<Long> receiverId;
    private Optional<Double> amount;
}
