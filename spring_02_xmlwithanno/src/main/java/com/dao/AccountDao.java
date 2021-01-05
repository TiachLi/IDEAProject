package com.dao;

import com.domain.Account;

import java.util.List;

public interface AccountDao {
    List<Account> findAll();
}
