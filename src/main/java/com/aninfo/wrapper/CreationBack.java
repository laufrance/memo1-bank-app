package com.aninfo.wrapper;
import com.aninfo.model.Account;

public class CreationBack {

    public CreationBack(Account account) {
        this.cbu = account.getCbu();
        this.balance = account.getBalance();
    }

    private Long cbu;
    private Double balance;
}
