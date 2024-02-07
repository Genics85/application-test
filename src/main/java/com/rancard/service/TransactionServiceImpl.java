package com.rancard.service;

import com.rancard.exception.TransactionNotFoundException;
import com.rancard.model.Transaction;
import com.rancard.model.dto.transaction.CreateTransactionDto;
import com.rancard.model.dto.transaction.UpdateTransactionDto;
import com.rancard.repository.TransactionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepo transactionRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public Transaction create(CreateTransactionDto createTransactionDto) {
        Transaction newTransaction = new Transaction();
//        newTransaction.setAccountNumber(createTransactionDto.getAccountNumber());
//        newTransaction.setTellerId(createTransactionDto.getTellerId());
//        newTransaction.setTransactionType(createTransactionDto.getTransactionType());
        newTransaction.setAmount(createTransactionDto.getAmount());

        return transactionRepo.save(newTransaction);
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public Transaction update(UpdateTransactionDto updateTransactionDto) {
        return null;
    }

    @Override
    public List<Transaction> list() {
        return null;
    }

    @Override
    public Transaction getById(Long id) {

        return transactionRepo.findById(id).orElseThrow(()-> new TransactionNotFoundException(id));
    }

}
