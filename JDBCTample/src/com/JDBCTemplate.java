package com;

import com.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTemplate {


   /* public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update user set password = '100123' where name = 'wangwu'";
        //3. 执行sql
        int count = template.update(sql);
        System.out.println(count);
    }*/
   @Test
    public void test1(){
       JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
       String sql = "update user set password = '1001223' where name = 'wangwu'";
       //3. 执行sql
       int count = template.update(sql);
       System.out.println(count);
   }
   @Test
   public void Test2(){

   }


}