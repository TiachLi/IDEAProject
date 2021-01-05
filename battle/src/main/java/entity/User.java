package entity;

import java.io.Serializable;

public class User implements Serializable{
	private String UserID;//用户登录ID
	private String name;//用户姓名
	private String password;//用户密码
	private String sex;//用户性别
	private String phone;//用户电话
	public String getUserID() {
		return UserID;
	}
	public String getname() {
		return name;
	}
	public String getpassword() {
	return password;
}
	public String getsex() {
	return sex;
}
	public String getphone() {
	return phone;
}
	public User() {
		
	}
	public User(String UserID,String name,String password,String sex,String phone) {
		this.UserID=UserID;
		this.name=name;
		this.password=password;
		this.sex=sex;
		this.phone=phone;
	}
	public User(String name,String password,String sex,String phone) {
		this.name=name;
		this.password=password;
		this.sex=sex;
		this.phone=phone;
	}
}
