package com.blw.Utils;
import javax.swing.colorchooser.ColorSelectionModel;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
public class DBUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;
    static{
        //读取资源文件，获取值。

        try {
            //1. 创建Properties集合类。
                Properties pro = new Properties();

                //获取src路径下的文件的方式--->ClassLoader 类加载器
                ClassLoader classLoader = DBUtils.class.getClassLoader();
                URL res  = classLoader.getResource("db.properties");
                String path = res.getPath();
                System.out.println(path);
                //2. 加载文件
                pro.load(new FileReader(path));

                //3. 获取数据，赋值
                url = pro.getProperty("url");
                user = pro.getProperty("user");
                password = pro.getProperty("password");
                driver = pro.getProperty("driver");
                //4. 注册驱动
                Class.forName(driver);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取连接
     * @return 连接对象
     */
    public static Connection getConnection()throws SQLException {
        conn =DriverManager.getConnection(url,user,password);
        return conn;
    }

    public void closeAll() {
        // 如果rs不空，关闭rs
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 如果pstmt不空，关闭pstmt
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 如果conn不空，关闭conn
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行SQL语句，可以进行查询
     */
    public ResultSet executeQuery(String preparedSql, Object[] param) {
        // 处理SQL,执行SQL
        try {
            // 得到PreparedStatement对象
            pstmt = conn.prepareStatement(preparedSql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    // 为预编译sql设置参数
                    pstmt.setObject(i + 1, param[i]);
                }
            }
            // 执行SQL语句
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            // 处理SQLException异常
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 执行SQL语句，可以进行增、删、改的操作，不能执行查询
     */
    public int executeUpdate(String preparedSql, Object[] param) {
        int num = 0;
        // 处理SQL,执行SQL
        try {
            // 得到PreparedStatement对象
            pstmt = conn.prepareStatement(preparedSql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    // 为预编译sql设置参数
                    pstmt.setObject(i + 1, param[i]);
                }
            }
            // 执行SQL语句
            num = pstmt.executeUpdate();
        } catch (SQLException e) {
            // 处理SQLException异常
            e.printStackTrace();
        }
        return num;
    }
}
