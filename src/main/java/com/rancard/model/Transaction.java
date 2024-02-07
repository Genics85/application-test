package com.rancard.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="transaction_sender",referencedColumnName = "id")
    private User sender;
    @ManyToOne
    @JoinColumn(name="transaction_receiver",referencedColumnName = "id")
    private User receiver;
    private double amount;
    @CreatedDate
    @Column(name="transaction_date")
    private LocalDateTime transactionDate = LocalDateTime.now();

}