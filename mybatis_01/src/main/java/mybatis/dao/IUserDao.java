package mybatis.dao;

import mybatis.domain.Admin;
import mybatis.domain.User;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;
import java.util.List;

public interface IUserDao {

    //@Select("select * from admin")
    List<Admin> findAll();
}
