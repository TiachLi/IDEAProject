package com.company;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user {

      public boolean Register(String name,String password){
          if(name == null || password == null){
              return false;
          }
          Connection con = null;
          PreparedStatement stat=null;
          ResultSet rs = null;
          try {
              con= JDBCTool.getConnection();
              String sql ="INSERT INTO user values(' "+name+ "',' "+password+ " ' )";
              stat = con.prepareStatement(sql);
              stat.executeUpdate();
              String Selectsql ="SELECT * from user WHERE name =? and password=?";
              stat = con.prepareStatement(Selectsql);
             stat.setString(1,name);
             stat.setString(2,password);
             rs = stat.executeQuery();
              return rs.next();
          } catch (SQLException e) {
              e.printStackTrace();
          }
          finally{
              JDBCTool.close(rs,stat,con);
          }
          return false;
      }

    public boolean Logic(String name,String password){
        if(name == null || password == null){
            return false;
        }
        Connection con = null;
        PreparedStatement stat=null;
        ResultSet rs = null;

        try {
            con=JDBCTool.getConnection();

            String Selectsql =" SELECT * from user WHERE name = ? and password = ? ";

            stat = con.prepareStatement(Selectsql);

            stat.setString(1,name);
            stat.setString(2,password);


            rs = stat.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCTool.close(rs,stat,con);
        }
        return false;
    }
}
