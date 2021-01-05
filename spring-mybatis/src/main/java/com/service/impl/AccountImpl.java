package com.service.impl;

import com.dao.AccountDao;
import com.domain.Account;
import com.service.AccountService;

public class AccountImpl implements AccountService {

    AccountDao accountDao;
    public void transfer(String source, String target, int money) {
        Account sourceAccount = accountDao.findAccountByName(source);
        Account targetAccount = accountDao.findAccountByName(target);

        sourceAccount.setMoney(sourceAccount.getMoney()-money);
        targetAccount.setMoney(targetAccount.getMoney()+money);

        accountDao.updateAccount(sourceAccount);
        accountDao.updateAccount(targetAccount);

    }
}
