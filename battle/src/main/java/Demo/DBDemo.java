package Demo;

import java.util.ArrayList;
import java.util.Date;

import Service.LogRecService;
import Service.TransportService;
import entity.DataBase;
import entity.LogRec;
import entity.MatchedLogRec;
import entity.MatchedTransport;
import entity.Transport;

public class DBDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		LogRecService logService=new LogRecService();
		ArrayList<MatchedLogRec>matchLogs=new ArrayList<>();
		matchLogs.add(new MatchedLogRec(
				new LogRec(5,new Date(),"广州",DataBase.GATHER,"222222","192.168.31.201",1),
				new LogRec(6,new Date(),"广州",DataBase.GATHER,"222222","192.168.31.201",0)
				));
		logService.saveMatchLogDB(matchLogs);
		ArrayList<MatchedLogRec>logList=logService.readMatchLogDB();
		logService.showMatchLog(logList);
		
		TransportService tranService=new TransportService();
		ArrayList<MatchedTransport>matchTrans =new ArrayList<>();
		matchTrans.add(new MatchedTransport(
				new Transport(1, new Date(), "河南省郑州市", DataBase.GATHER, "111111",50,1)
				));
		matchTrans.add(new MatchedTransport(
				new Transport(2, new Date(), "河南省南阳市", DataBase.GATHER, "111111",80,2)
				));
		matchTrans.add(new MatchedTransport(
				new Transport(3, new Date(), "北京市", DataBase.GATHER, "222222",100,3)
				));
		matchTrans.add(new MatchedTransport(
				new Transport(4, new Date(), "广州省深圳市", DataBase.GATHER, "222222",30,1)
				));
		tranService.saveMatchTransportDB(matchTrans);
		ArrayList<MatchedTransport>transportList=tranService.readMatchTransDB();
		tranService.showMatchTransport(transportList);
	}
		
}
