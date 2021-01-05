import dao.BaseDao;
import dao.impl.BaseDaoImpl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        BaseDao baseDao = new BaseDaoImpl();
        Connection conn = baseDao.getConnection();
        String selectSql = "select ID,PSW from [User]";
        List<Map<String,Object>> rs  = baseDao.executeQuery(selectSql);
    }
}
