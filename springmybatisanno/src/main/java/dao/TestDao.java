package dao;

import bean.Account;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao {

    @Select("select * from account")
    List<Account> findAll();
}
