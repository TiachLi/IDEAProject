package com.dao;

import com.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AccountDao {
    public List<Account> findAll();
    public void updateAccount(Account account);
    public Account findAccountByName(String name);
}
