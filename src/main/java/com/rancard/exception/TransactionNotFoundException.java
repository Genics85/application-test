package com.rancard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException(Long id) {
        super(String.format("Transaction with id: %s not found",id));

    }

}
