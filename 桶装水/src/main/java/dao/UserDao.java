package dao;

public interface UserDao extends BaseDao{
    public int insertUser(String ID,String PSW);
    public Boolean checkingAccount(String ID,String PSW);
}
