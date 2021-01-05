package jdbcDemo;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcDemo1 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sql="insert into test1 values ('zhangsan',null,7),('zhangsan',null,7)";
            Connection con=DriverManager.getConnection("jdbc:mysql:///test1","root","wl1234536.");
            Statement stmt=con.createStatement();
            int c =stmt.executeUpdate(sql);
            System.out.println(c);
            stmt.close();
            con.close();
            Scanner sc= new Scanner(System.in);
            String s = sc.next();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
