package dao;

import domain.Account;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
public interface IAccountDao {

    /**
     * 查询所有账户，并且获取每个账户所属的用户信息
     * @return
     */
    List<Account> findAll();
    /**
     * 根据用户id查询账户信息
     * @param userId
     * @return
     */
    List<Account> findByUid(Integer userId);
    void addAccount(Account account);
}
