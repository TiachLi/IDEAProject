package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Config;

public class DButil {
	Connection con = null;
	 PreparedStatement sql=null;
	 ResultSet rs=null;
	 public Connection getConnection() {
		 String driver=Config.getValue("driver");
			String uri=Config.getValue("uri");
			String user=Config.getValue("user");
			String password=Config.getValue("password");
			  try
			 {
			     Class.forName(driver);
			     System.out.println("驱动加载成功");
			 }catch(Exception e){
			     e.printStackTrace();
			     System.out.println("驱动加载失败");
			 }
			 try{
			        con=DriverManager.getConnection(uri,user,password);
			         System.out.println("连接成功");
			        
			         
			 }catch(Exception e)
			 {
			     e.printStackTrace();
			     System.out.print("SQL Server连接失败");
			 } 
			 return con;
	 }
	 public void closeAll() {
		 if(rs!=null) {
			 try {rs.close();}
			 catch(SQLException e) {
				 e.printStackTrace();
			 }
		 }
		 if(sql!=null) {
			 try {sql.close();}
			 catch(SQLException e) {
				 e.printStackTrace();
			 }
		 }
		 if(con!=null) {
			 try {con.close();}
			 catch(SQLException e) {
				 e.printStackTrace();
			 }
		 }
	 }
	 
	 public ResultSet executeQuery(String pre,Object[] p) {
		 try {
			 sql=con.prepareStatement(pre);
			 if(p!=null) {
				 for(int i=0;i<p.length;i++)
					 sql.setObject(i+1,p[i]);
			 }
			 rs=sql.executeQuery();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return rs;
	 }
	 public int executeUpdate(String pre,Object[] p) {
		 int num=0;
		 try {
			 sql=con.prepareStatement(pre);
			 if(p!=null) {
				 for(int i=0;i<p.length;i++)
					 sql.setObject(i+1,p[i]);
			 }
			 num=sql.executeUpdate();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return num;
	 }
}
