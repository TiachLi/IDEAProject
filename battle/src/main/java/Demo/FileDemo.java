package Demo;
import entity.*;


import java.util.ArrayList;
import java.util.Date;

import Service.*;
public class FileDemo {
public static void main(String []args) {
	LogRecService logService=new LogRecService();
	ArrayList<MatchedLogRec>matchLogs=new ArrayList<>();
	matchLogs.add(new MatchedLogRec(
			new LogRec(1001,new Date(),"Заµє",DataBase.GATHER,"zhangsan","192.168.0.103",1),
			new LogRec(1002,new Date(),"Заµє",DataBase.GATHER,"zhangsan","192.168.0.103",0)
			));
	logService.saveMatchLog(matchLogs);
	ArrayList<MatchedLogRec>logList=logService.readMatchLog();
	logService.showMatchLog(logList);
	
	TransportService tranService=new TransportService();
	ArrayList<MatchedTransport>matchTrans =new ArrayList<>();
	matchTrans.add(new MatchedTransport(
			new Transport(2001, new Date(), "Заµє", DataBase.GATHER, "zhangsan",100,3)
			));
	tranService.saveMatchTransport(matchTrans);
	ArrayList<MatchedTransport>transportList=tranService.readMatchTrans();
	tranService.showMatchTransport(transportList);
}
}
