package com.rancard.service;

import com.rancard.exception.TransactionNotFoundException;
import com.rancard.model.Transaction;
import com.rancard.model.User;
import com.rancard.model.dto.transaction.CreateTransactionDto;
import com.rancard.model.dto.transaction.UpdateTransactionDto;
import com.rancard.repository.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepo transactionRepo;
    private final UserService userService;

    public TransactionServiceImpl(TransactionRepo transactionRepo, UserService userService) {
        this.userService = userService;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public Transaction create(CreateTransactionDto createTransactionDto) {

        User sender = userService.getById(createTransactionDto.getSenderId());
        User receiver = userService.getById(createTransactionDto.getReceiverId());

        double amount = Math.abs(createTransactionDto.getAmount());

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSender(sender);
        transaction.setReceiver(receiver);

        transactionRepo.save(transaction);

        return transaction;
    }

    @Override
    public String delete(Long id) {

        this.getById(id);

        transactionRepo.deleteById(id);

        return "Deleted";
    }

    @Override
    public Transaction update(UpdateTransactionDto updateTransactionDto) {
        Transaction transaction = this.getById(updateTransactionDto.getId());

        if(updateTransactionDto.getReceiverId().isPresent()){
            Long receiverId = updateTransactionDto.getReceiverId().get();
            User newReceiver = userService.getById(receiverId);
            transaction.setReceiver(newReceiver);
        }
        if(updateTransactionDto.getSenderId().isPresent()){
            Long senderId = updateTransactionDto.getSenderId().get();
            User newSender = userService.getById(senderId);
            transaction.setSender(newSender);
        }
        if(updateTransactionDto.getAmount().isPresent()){
            Double newAmount = updateTransactionDto.getAmount().get();
            transaction.setAmount(Math.abs(newAmount));
        }

        transactionRepo.save(transaction);

        return transaction;
    }

    @Override
    public List<Transaction> list( Pageable pageable) {

        return transactionRepo.findAll(pageable).toList();
    }

    @Override
    public Transaction getById(Long id) {

        return transactionRepo.findById(id).orElseThrow(()-> new TransactionNotFoundException(id));
    }

}
