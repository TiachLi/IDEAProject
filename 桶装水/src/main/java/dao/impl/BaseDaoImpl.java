package dao.impl;

import config.Config;
import dao.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl implements BaseDao {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    //获得连接
    public Connection getConnection() {
        conn = null;
        String DRIVER = Config.getValue("DRIVER");
        String URL = Config.getValue("URL");
        String USER = Config.getValue("USER");
        String PASSWORD = Config.getValue("PASSWORD");
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 再次连接
     */
    public void Connect() {
        getConnection();
    }
    /**
     * 判断Connection是否连接
     */
    public Boolean isConnect() throws  Exception{
        if (conn.isClosed())
            return false;
        else{
            return true;
        }
    }
    /**
     * 关闭资源
     */
    public void closeAll() {
        if(rs!=null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(ps!=null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 动态执行sql
     * @param sql:预编译的sql
     * @param objs:可变参数
     */
    public int executeSql(String sql,Object...objs) {
        //获得连接
        int influence = 0;
        try {
            ps=conn.prepareStatement(sql);
            //设置参数
            for(int i=0;i<objs.length;i++) {
                ps.setObject(i+1, objs[i]);
            }
            //执行sql
            boolean hasResult = ps.execute();
            if (!hasResult){
                influence = ps.getUpdateCount();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return influence;
    }

    /**
     * 执行一条select语名，并且将返回结果封装到List中，每条记录对一个Map
     * @param sql:sql查询语句，可带参数(?)
     * @param objs:可变参数
     * @return
     */
    public List<Map<String,Object>> executeQuery(String sql, Object...objs){
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        try {
            ps=conn.prepareStatement(sql);
            for(int i=0;i<objs.length;i++) {
                ps.setObject(i+1, objs[i]);
            }
            //执行查询
            rs=ps.executeQuery();
            //获得元数据
            ResultSetMetaData rsmd=rs.getMetaData();
            //将每条记录转成一个Map,key是列名，value是字段值
            while(rs.next()) {
                Map<String,Object> map=new HashMap<String,Object>();
                for(int i=1;i<=rsmd.getColumnCount();i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            this.closeAll();
        }
        return list;
    }
}
