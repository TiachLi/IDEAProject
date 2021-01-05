package com.service;

import com.domain.Account;

import java.util.List;

public interface AccountService {
    public void init();
    public List<Account> findAllService();
}
