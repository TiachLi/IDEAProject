package dao.impl;

import dao.UserDao;
import dao.impl.BaseDaoImpl;

import java.util.List;
import java.util.Map;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    public int insertUser(String ID,String PSW){
        String sql = "insert into [USER](ID,PSW) values(?,?);";
        int influence;
        String[] value = new String[2];
        value[0] = ID;
        value[1] = PSW;
        influence = executeSql(sql,value);
        return influence;
    }
    public Boolean checkingAccount(String ID,String PSW){
        String sql = "select ID,PSW from [User] Where ID = " + "'" + ID +"'";
        List<Map<String,Object>> rs = this.executeQuery(sql);
        if (rs.size() != 0)
        {
            String psw = (String) rs.get(0).get("PSW");
            if (psw.trim().equals(PSW))
                return true;
            else
                return false;
        }else {
            return false;
        }
    }
}
