package com.aninfo.integration.cucumber;

import com.aninfo.Memo1BankApp;
import com.aninfo.model.Account;
import com.aninfo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = Memo1BankApp.class)
@WebAppConfiguration
public class AccountIntegrationServiceTest {

    @Autowired
    BankService bankService;

    Account createAccount(Double balance) {

        return bankService.createAccount(balance);
    }

    Account withdraw(Account account, Double sum) {

        return bankService.withdraw(account.getCbu(), sum);
    }

    Account deposit(Account account, Double sum) {

        return bankService.deposit(account.getCbu(), sum);
    }

    Account promo_deposit(Account account, Double sum) {
        return bankService.promo_deposit(account.getCbu(), sum);
    }

}
