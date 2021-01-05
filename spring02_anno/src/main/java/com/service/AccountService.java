package com.service;

import com.domain.Account;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    public void init();
    public List<Account> findAllService();
}
