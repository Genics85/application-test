package com.rancard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException{
    private Long id;

    public TransactionNotFoundException(Long id) {
        super(String.format("Transaction not found with id: %s",id));

        this.id = id;
    }
}
