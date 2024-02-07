package com.rancard.controller;

import com.rancard.model.Transaction;
import com.rancard.model.dto.transaction.CreateTransactionDto;
import com.rancard.model.dto.transaction.UpdateTransactionDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

interface TransactionController {

    ResponseEntity<Transaction > getById(Long id);

    ResponseEntity<String> delete(Long id);

    ResponseEntity<List<Transaction>> list();

    ResponseEntity<Transaction> update(UpdateTransactionDto updateTransactionDto);

    ResponseEntity<Transaction> create(CreateTransactionDto createTransactionDto);
}
