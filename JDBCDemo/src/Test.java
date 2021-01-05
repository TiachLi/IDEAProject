import java.sql.*;

public class Test {
    public static void main(String[] args) {
        try {
            ResultSet rs=null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:///test1","root","wl1234536.");
            String sql  = "select * from user where name =? ";
           PreparedStatement st =con.prepareStatement(sql);
           st.setString(1,"zhangsan");

            rs =st.executeQuery();

            System.out.println( rs.next());

            /*String sql  = "select * from test1 where name='wl' ";
            Statement st =con.createStatement();
            rs =st.executeQuery(sql);
            rs.next();*/
            System.out.println( rs.getString(1));
            con.close();
            st.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
