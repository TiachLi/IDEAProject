package dao;

import domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
@CacheNamespace(blocking = true)
public interface IAccountDao {

    /**
     * 查询所有账户，并且获取每个账户所属的用户信息
     * @return
     */
     @Select("select * from account")
     @Results(id = "accountMap",value = {
             @Result(id = true,property = "id",column = "id"),
             @Result(property = "uid",column = "uid"),
             @Result(property = "money",column = "money"),
             @Result(property = "user",column = "uid",many = @Many(select = "dao.IUserDao.findById",fetchType = FetchType.LAZY))
     })
    List<Account> findAll();
    /**
     * 根据用户id查询账户信息
     * @param userId
     * @return
     */
    @Select("select * from account where uid=#{id}")
    List<Account> findByUid(Integer userId);
}
