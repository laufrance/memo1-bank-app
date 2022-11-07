package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionModels;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(double balance) {
        return accountRepository.save(new Account(balance));
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        Transaction transaction = new Transaction(TransactionModels.WITHDRAW, sum);
        account.setBalance(account.getBalance() - sum);
        account.addTransaction(transaction);
        accountRepository.save(account);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        Account account = accountRepository.findAccountByCbu(cbu);

        Transaction transaction = new Transaction(TransactionModels.DEPOSIT, sum);

        account.setBalance(account.getBalance() + sum);
        account.addTransaction(transaction);
        accountRepository.save(account);

        return account;
    }

    public List<Transaction> getTransactionsFromAccount(Long cbu) {
        return accountRepository.findAccountByCbu(cbu).getTransactionList();
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.findAccountById(id);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Transactional
    public Account promo_deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }
        if (sum >= 2000) {
            if (sum * 0.1 >= 500) {
                sum += 500;
            } else {
                sum += sum * 0.1;
            }
        }
        Account account = accountRepository.findAccountByCbu(cbu);
        account.setBalance(account.getBalance() + sum);
        accountRepository.save(account);

        return account;
    }

}
