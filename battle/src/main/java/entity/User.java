package entity;

import java.io.Serializable;

public class User implements Serializable{
	private String UserID;//�û���¼ID
	private String name;//�û�����
	private String password;//�û�����
	private String sex;//�û��Ա�
	private String phone;//�û��绰
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
