package com.company;

import javax.swing.colorchooser.ColorSelectionModel;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCTool {
    /*private static String url;
    private static String user;
    private static String password;
    private static String driver;*/

        static{
            //读取资源文件，获取值。

            try {
                //1. 创建Properties集合类。
              /*  Properties pro = new Properties();

                //获取src路径下的文件的方式--->ClassLoader 类加载器
                ClassLoader classLoader = JDBCTool.class.getClassLoader();
                URL res  = classLoader.getResource("jdbc.properties");
                String path = res.getPath();
                // System.out.println(path);///D:/IdeaProjects/itcast/out/production/day04_jdbc/jdbc.properties
                //2. 加载文件
                // pro.load(new FileReader("D:\\IdeaProjects\\itcast\\day04_jdbc\\src\\jdbc.properties"));
                pro.load(new FileReader(path));

                //3. 获取数据，赋值
                url = pro.getProperty("url");
                user = pro.getProperty("user");
                password = pro.getProperty("password");
                driver = pro.getProperty("driver");
                //4. 注册驱动
                Class.forName(driver);*/
              Class.forName("com.mysql.jdbc.Driver");
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        /**
         * 获取连接
         * @return 连接对象
         */
        public static Connection getConnection()throws SQLException {
            return DriverManager.getConnection("jdbc:mysql:///test1","root","wl1234536.");
        }

    /**
     * 释放资源
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn){
        if( rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if( stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if( conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 释放资源
     * @param stmt
     * @param conn
     */
    public static void close( PreparedStatement stmt,Connection conn){
        if( stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if( conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
