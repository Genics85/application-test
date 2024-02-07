package com.rancard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus( value = HttpStatus.NOT_MODIFIED)
public class LowBalanceException extends RuntimeException{
    private String accountNumber;
    private String amount;

    public LowBalanceException(String accountNumber, String amount) {
        super(String.format("%s can't withdraw %s : %s",accountNumber,amount));
        this.accountNumber = accountNumber;
        this.amount = amount;
    }
}