package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户的持久层实现类
 */
@Repository("accountDao")
public class AccountDaoImpl  implements IAccountDao {

    @Autowired
    JdbcTemplate template =new JdbcTemplate();
    public Account findAccountById(Integer accountId) {
        List<Account> accounts = template.query("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class),accountId);
        return accounts.isEmpty()?null:accounts.get(0);
    }


    public Account findAccountByName(String accountName) {
        List<Account> accounts = template.query("select * from account where name = ?",new BeanPropertyRowMapper<Account>(Account.class),accountName);
        if(accounts.isEmpty()){
            return null;
        }
        if(accounts.size()>1){
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }


    public void updateAccount(Account account) {
        template.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
    }
}
