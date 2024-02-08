package com.rancard.service;

import com.rancard.model.Transaction;
import com.rancard.model.dto.transaction.CreateTransactionDto;
import com.rancard.model.dto.transaction.UpdateTransactionDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    Transaction create(CreateTransactionDto createTransactionDto);

    String delete(Long id);

    Transaction update( UpdateTransactionDto updateTransactionDto);

    List<Transaction > list(Pageable pageable);

    Transaction getById(Long id);

}
