package dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface BaseDao {
    public Connection getConnection();
    public void Connect();
    public void closeAll();
    public int executeSql(String sql,Object...objs);
    public List<Map<String,Object>> executeQuery(String sql, Object...objs);
}
