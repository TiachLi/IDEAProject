package Service;
import entity.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import db.DButil;

public class LogRecService {
	// ��־���ݲɼ�
		public LogRec inputLog() {
			LogRec log = null;
			// ����һ���Ӽ��̽������ݵ�ɨ����
			Scanner scanner = new Scanner(System.in);
			try{
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
				System.out.println("������ ��¼�û�����");
				// ���ռ���������ַ�����Ϣ
				String user = scanner.next();
				// ��ʾ�û���������IP
				System.out.println("������ ����IP:");
				// ���ռ���������ַ�����Ϣ
				String ip = scanner.next();
				// ��ʾ�û������¼״̬���ǳ�״̬
				System.out.println("�������¼״̬:1�ǵ�¼��0�ǵǳ�");
				int logType = scanner.nextInt();
				// ������־����
				log = new LogRec(id, nowDate, address, type, user, ip, logType);
			} catch (Exception e) {
				System.out.println("�ɼ�����־��Ϣ���Ϸ�");
			}
			// ������־����
			return log;
		}

		// ��־��Ϣ���
		public void showLog(LogRec... logRecs) {
			for (LogRec e : logRecs) {
				if (e != null) {
					System.out.println(e.toString());
				}
			}
		}

		// ƥ����־��Ϣ������ɱ����
		public void showMatchLog(MatchedLogRec... matchLogs) {
			for (MatchedLogRec e : matchLogs) {
				if (e != null) {
					System.out.println(e.toString());
				}
			}
		}
		// ƥ����־��Ϣ���,�����Ǽ���
		public void showMatchLog(ArrayList<MatchedLogRec> matchLogs) {
			for (MatchedLogRec e : matchLogs) {
				if (e != null) {
					System.out.println(e.toString());
				}
			}
		}

public void saveMatchLog(ArrayList <MatchedLogRec> matchLogs) {
	try(ObjectOutputStream obs=new ObjectOutputStream(new FileOutputStream("MatchLogs.txt",true))){
		for(MatchedLogRec e:matchLogs) {
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
public ArrayList<MatchedLogRec>readMatchLog(){
	ArrayList<MatchedLogRec>matchLogs=new ArrayList<>();
	try(ObjectInputStream osi=new ObjectInputStream(new FileInputStream("MatchLogs.txt"))){
		MatchedLogRec matchLog;
		while((matchLog=(MatchedLogRec)osi.readObject())!=null) {
			matchLogs.add(matchLog);
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	return matchLogs;
}
public void saveMatchLogDB(ArrayList <MatchedLogRec> matchLogs) {
	DButil db=new DButil();
	try {
		db.getConnection();
		for(MatchedLogRec matchedLogRec:matchLogs) {
			LogRec login=matchedLogRec.getLogin();
			LogRec logout=matchedLogRec.getLogout();
			String sql="insert into [dbo].[LOGREC](id,time,adress,type,userID,ip,logtype) "
					+ "values(?,?,?,?,?,?,?)";
			Object[] p=new Object[] {
					login.getId(),new Date(),login.getAddress(),
					login.getType(),login.getUser(),login.getIp(),login.getLogType()};
			db.executeUpdate(sql, p);
			p=new Object[] {
					logout.getId(),new Date(),logout.getAddress(),
					logout.getType(),logout.getUser(),logout.getIp(),logout.getLogType()};
			db.executeUpdate(sql, p);
			}
		db.closeAll();
		}
	catch(Exception e) {
		e.printStackTrace();
		
	}
}
public ArrayList<MatchedLogRec>readMatchLogDB(){
	ArrayList<MatchedLogRec>matchLogs=new ArrayList<MatchedLogRec>();
	DButil db=new DButil();
	try {
		db.getConnection();
		String sql="select i.id,i.time,i.adress,i.type,i.userID,i.ip,i.logtype,"
				+ "o.id,o.time,o.adress,o.type,o.userID,o.ip,o.logtype "
				+ "from [dbo].[MATCHED_LOGREC] m,[dbo].[LOGREC] i,[dbo].[LOGREC] o "
				+ "where m.loginid=i.id and m.logoutid=o.id";
		ResultSet rs=db.executeQuery(sql, null);
		while(rs.next()) {
			LogRec login=new LogRec(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getInt(4),rs.getString(5)
					,rs.getString(6),rs.getInt(7));
			LogRec logout=new LogRec(rs.getInt(8),rs.getDate(9),rs.getString(10),rs.getInt(11),rs.getString(12)
					,rs.getString(13),rs.getInt(14));
			MatchedLogRec matchedLog=new MatchedLogRec(login,logout);
			matchLogs.add(matchedLog);
		}
		db.closeAll();
	}catch(Exception e) {
		e.printStackTrace();
	}
	return matchLogs;
}
public ResultSet readLogResult() {
	DButil db=new DButil();
	ResultSet rs=null;
	try {
		Connection con=db.getConnection();
		Statement str=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sql="select * from [dbo].[LOGREC]";
		rs=str.executeQuery(sql);
	}catch(Exception e) {
		e.printStackTrace();
	}
	return rs;
}
}
