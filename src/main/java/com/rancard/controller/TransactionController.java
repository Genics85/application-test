package com.rancard.controller;

import com.rancard.model.Transaction;
import com.rancard.model.dto.transaction.CreateTransactionDto;
import com.rancard.model.dto.transaction.UpdateTransactionDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

interface TransactionController {

    @GetMapping("/{id}")

    ResponseEntity<Transaction > getById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id);

    @GetMapping("/list")
    ResponseEntity<List<Transaction>> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "20") int size,
                                           @RequestParam(value = "sortBy", defaultValue = "id") String sortBy);

    @PatchMapping
    ResponseEntity<Transaction> update(@RequestBody @Valid UpdateTransactionDto updateTransactionDto);

    @PostMapping
    ResponseEntity<Transaction> create(@RequestBody @Valid CreateTransactionDto createTransactionDto);
}
