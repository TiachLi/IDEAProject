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
	// �������ݲɼ�
		public Transport inputTransport() {
			// ����һ���Ӽ��̽������ݵ�ɨ����
			Scanner scanner = new Scanner(System.in);
			// ��ʾ�û�����ID��ʶ
			System.out.println("������ID��ʶ��");
			// ���ռ������������
			int id = scanner.nextInt();
			// ��ȡ��ǰϵͳʱ��
			Date nowDate = new Date();
			// ��ʾ�û������ַ
			System.out.println("�������ַ��");
			// ���ռ���������ַ�����Ϣ
			String address = scanner.next();
			// ����״̬�ǡ��ɼ���
			int type = DataBase.GATHER;

			// ��ʾ�û������¼�û���
			System.out.println("�������û�ID��");
			// ���ռ���������ַ�����Ϣ
			String UserID = scanner.next();
			// ��ʾ�û���������IP
			System.out.println("�����빺�������:");
			// ���ռ���������ַ�����Ϣ
			int amount = scanner.nextInt();
			// ��ʾ������������״̬
			System.out.println("�����빺��Ĳ�Ʒ����1ũ��ɽȪ��2������3����ɽ");
			// ��������״̬
			int producename = scanner.nextInt();
			// ����������Ϣ����
			Transport trans = new Transport(id, nowDate, address, type, UserID,
					amount, producename);
			// ������������
			return trans;
		}

		// ������Ϣ���
		public void showTransport(Transport... transports) {
			for (Transport e : transports) {
				if (e != null) {
					System.out.println(e.toString());
				}
			}
		}

		// ƥ���������Ϣ���
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
