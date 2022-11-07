package com.aninfo.wrapper;

public class CreationForward {
    public Double balance;
    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CreationForward() {
    }

    public CreationForward(double balance) {
        this.balance = balance;
    }
}
