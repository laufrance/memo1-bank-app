package com.aninfo.model;


import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public TransactionModels getTransactionType() {
        return this.transactionType;
    }

    public Transaction() {
    }

    public Transaction(TransactionModels transactionType, Double sum) {
        this.transactionType= transactionType;
        this.sum = sum;
    }

    @Enumerated(EnumType.STRING)
    private TransactionModels transactionType;

    private Double sum;

    public Double getSum() {
        return this.sum;
    }
    public Long getId() {
        return this.id;
    }

}
