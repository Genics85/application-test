package com.rancard.controller;

import com.rancard.model.Transaction;
import com.rancard.model.dto.transaction.CreateTransactionDto;
import com.rancard.model.dto.transaction.UpdateTransactionDto;
import com.rancard.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionControllerImpl implements TransactionController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TransactionService service;


    @Override
    public ResponseEntity<Transaction> getById(Long id) {
        log.info("http request: getById Transaction");

        Transaction transaction = service.getById(id);

        return ResponseEntity.ok(transaction);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        log.info("http request: delete Transaction");

        String message = service.delete(id);

        return ResponseEntity.ok(message);
    }

    @Override
    public ResponseEntity<List<Transaction>> list(int page,int size,String sortBy) {
        log.info("http request: list Transaction");

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));

        List<Transaction> transactions = service.list(pageRequest);

        return ResponseEntity.ok(transactions);
    }

    @Override
    public ResponseEntity<Transaction> update(UpdateTransactionDto updateTransactionDto) {
        log.info("http request: update Transaction");

        Transaction transaction = service.update(updateTransactionDto);

        return ResponseEntity.ok(transaction);
    }

    @Override
    public ResponseEntity<Transaction> create(CreateTransactionDto createTransactionDto) {
        log.info("http request: create Transaction");

        Transaction transaction = service.create(createTransactionDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);

    }
}
