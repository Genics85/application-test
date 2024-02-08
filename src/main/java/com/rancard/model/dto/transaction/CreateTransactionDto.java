package com.rancard.model.dto.transaction;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTransactionDto {
    @NotNull(message = "sender id must be provided, type of Long")
    private Long senderId;
    @NotNull(message = "Receiver id must be provided, type of Long")
    private Long receiverId;
    @NotNull
    @Min(value = 1, message = "Transaction amount must be provided, not less than 1")
    private Double amount;
}
