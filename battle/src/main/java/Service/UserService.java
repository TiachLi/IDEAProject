package Service;

import java.sql.ResultSet;

import db.DButil;
import entity.*;

public class UserService {
	public User findUserByName(String userID){
		DButil db=new DButil();
		User user=null;
		try {
			db.getConnection();
			String sql="select * from [dbo].[User] where userID=?";
			Object[] p=new Object[] {userID};
			ResultSet rs=db.executeQuery(sql, p);
			if(rs.next()) {
				user=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				
			}
					}catch(Exception e) {
						e.printStackTrace();
					}
		finally {
			db.closeAll();
		}
		return user;
	}
	public boolean saveUser(User user) {
		boolean r=false;
		DButil db=new DButil();
		try {
			db.getConnection();
			String sql="insert into [dbo].[User](userID,name,password,sex,phone) values("
					+ "?,?,?,?,?)";
			Object[] p=new Object[] {user.getUserID(),user.getname(),user.getpassword(),user.getsex(),
					user.getphone()};
			if(db.executeUpdate(sql, p)>0) {
				r=true;
			}
			}catch(Exception e) {
						e.printStackTrace();
		    }finally {
						db.closeAll();
					}
		return r;
	}
}
