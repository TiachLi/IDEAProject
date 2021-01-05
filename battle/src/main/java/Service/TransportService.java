package Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import db.DButil;
import entity.*;

public class TransportService {
	// 物流数据采集
		public Transport inputTransport() {
			// 建立一个从键盘接收数据的扫描器
			Scanner scanner = new Scanner(System.in);
			// 提示用户输入ID标识
			System.out.println("请输入ID标识：");
			// 接收键盘输入的整数
			int id = scanner.nextInt();
			// 获取当前系统时间
			Date nowDate = new Date();
			// 提示用户输入地址
			System.out.println("请输入地址：");
			// 接收键盘输入的字符串信息
			String address = scanner.next();
			// 数据状态是“采集”
			int type = DataBase.GATHER;

			// 提示用户输入登录用户名
			System.out.println("请输入用户ID：");
			// 接收键盘输入的字符串信息
			String UserID = scanner.next();
			// 提示用户输入主机IP
			System.out.println("请输入购买的数量:");
			// 接收键盘输入的字符串信息
			int amount = scanner.nextInt();
			// 提示用于输入物流状态
			System.out.println("请输入购买的产品名：1农夫山泉，2怡宝，3百岁山");
			// 接收物流状态
			int producename = scanner.nextInt();
			// 创建物流信息对象
			Transport trans = new Transport(id, nowDate, address, type, UserID,
					amount, producename);
			// 返回物流对象
			return trans;
		}

		// 物流信息输出
		public void showTransport(Transport... transports) {
			for (Transport e : transports) {
				if (e != null) {
					System.out.println(e.toString());
				}
			}
		}

		// 匹配的物流信息输出
		public void showMatchTransport(MatchedTransport... matchTrans) {
			for (MatchedTransport e : matchTrans) {
				if (e != null) {
					System.out.println(e.toString());
				}
			}
		}
		public void showMatchTransport(ArrayList<MatchedTransport> matchTrans) {
			for (MatchedTransport e : matchTrans) {
				if (e != null) {
					System.out.println(e.toString());
				}
			}
		}
		
		public void saveMatchTransport(ArrayList <MatchedTransport> matchTrans) {
			try(ObjectOutputStream obs=new ObjectOutputStream(new FileOutputStream("MatchTransports.txt",true))){
				for(MatchedTransport e:matchTrans) {
					if(e!=null) {
						obs.writeObject(e);
					obs.flush();
					}
					
				}
				obs.writeObject(null);
				obs.flush();
			}
			
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}

public ArrayList<MatchedTransport>readMatchTrans(){
	ArrayList<MatchedTransport>matchLogs=new ArrayList<>();
	try(ObjectInputStream osi=new ObjectInputStream(new FileInputStream("MatchTransports.txt"))){
		MatchedTransport matchTran;
		while((matchTran=(MatchedTransport)osi.readObject())!=null) {
			matchLogs.add(matchTran);
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	return matchLogs;
	
}
public void saveMatchTransportDB(ArrayList <MatchedTransport> matchTrans) {
	DButil db=new DButil();
	try {
		db.getConnection();
		for(MatchedTransport matchedTransport:matchTrans) {
			Transport login=matchedTransport.getSend();
			String sql="insert into [dbo].[transport](id,time,adress,type,userID,amount,producename) "
					+ "values(?,?,?,?,?,?,?)";
			Object[] p=new Object[] {
					login.getId(),new Date(),login.getAddress(),
					login.getType(),login.getHandler(),login.getReciver(),login.getTransportType()};
			db.executeUpdate(sql, p);
			}
		db.closeAll();
		}
	catch(Exception e) {
		e.printStackTrace();
		
	}
}
public ArrayList<MatchedTransport>readMatchTransDB(){
	ArrayList<MatchedTransport>matchLogs=new ArrayList<>();
	DButil db=new DButil();
	try {
		db.getConnection();
		String sql="select id,time,adress,type,userID,amount,producename "
				+ "from [dbo].[transport] ";
		ResultSet rs=db.executeQuery(sql, null);
		while(rs.next()) {
			Transport login=new Transport(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getInt(4),rs.getString(5)
					,rs.getInt(6),rs.getInt(7));
			MatchedTransport matchedLog=new MatchedTransport(login);
			matchLogs.add(matchedLog);
		}
		db.closeAll();
	}catch(Exception e) {
		e.printStackTrace();
	}
	return matchLogs;
}
public ResultSet readTranResult() {
	DButil db=new DButil();
	ResultSet rs=null;
	try {
		Connection con=db.getConnection();
		Statement str=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sql="select * from [dbo].[TRANSPORT]";
		rs=str.executeQuery(sql);
	}catch(Exception e) {
		e.printStackTrace();
	}
	return rs;
}
}
