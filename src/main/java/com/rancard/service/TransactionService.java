package com.rancard.service;

import com.rancard.model.Transaction;
import com.rancard.model.dto.transaction.CreateTransactionDto;
import com.rancard.model.dto.transaction.UpdateTransactionDto;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    Transaction create(CreateTransactionDto createTransactionDto);

    String delete(Long id);

    Transaction update(UpdateTransactionDto updateTransactionDto);

    List<Transaction > list();

    Transaction getById(Long id);

}
