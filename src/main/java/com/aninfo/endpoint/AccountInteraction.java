package com.aninfo.endpoint;

import com.aninfo.model.Account;

public class AccountInteraction {
    private Long cbu;
    private Double balance;

    public AccountInteraction(Account account) {
        this.cbu = account.getCbu();
        this.balance = account.getBalance();
    }
}