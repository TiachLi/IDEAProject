package Demo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;

import Service.*;
import entity.*;
import gather.*;
import util.Config;
public class OrderFrame extends JFrame{

	private JMenuBar menuBar;
	private JMenu menuOperate, menuelp, menuMatch;
	private JMenuItem miGather, miMatchLog, miMatchTrans, miSave, miSend, 
	miShow, miExit, miCheck, miAbout;
	private JTabbedPane tpGather, showPane;
	private JPanel p, pLog, pTran, pLogId, pName, pLocation, pIP, pLogStatus, 
	pLogButton, pTransId, pAdress, pHandler, pReceiver, pTranStatus, pTranButton;
	private JLabel lblLogId, lblName, lblLocation, lblIP,lblLogStatus, 
	lblTransId,lblAdress,lblHandler, lblReceiver, lblTranStatus;
	private JTextField txtLogId, txtName, txtLocation, txtIP, txtTransId, 
	txtAdress, txtHandler, txtReceiver;
	private JRadioButton rbLogin, rbLogout;
	private JButton btnLogConfirm, btnLogReset, btnTranConfirm, btnTranReset, 
	btnGather, btnMatchLog, btnMatchTrans, btnSave, btnSend, btnShow;
	private JComboBox < String > cmbTanStatus;
	private JToolBar toolBar;
	private JScrollPane scrollPane;
	private CardLayout card;
	private LogRec log;
	private Transport trans;
	private ArrayList <LogRec>logList;
	private ArrayList < Transport> transList;
	private ArrayList< MatchedLogRec > matchedLogs;
	private ArrayList < MatchedTransport> matchedTrans;
	private LogRecService logRecService;
	private TransportService transportService;
	private String serverIP;
	public OrderFrame() {
		
	super("系统客户端");
	logList=new ArrayList < LogRec >();
	transList = new ArrayList <Transport>();
	matchedLogs = new ArrayList <MatchedLogRec >();
	matchedTrans = new ArrayList < MatchedTransport>();
	logRecService = new LogRecService();
	transportService = new TransportService();
	initToolBar();
	card = new CardLayout();
	p = new JPanel();
	p.setLayout(card);
	tpGather=new JTabbedPane(JTabbedPane.TOP);
	showPane=new JTabbedPane(JTabbedPane.TOP);
	showPane.addTab("日志",new JScrollPane());
	showPane.addTab("物流", new JScrollPane());
	p.add( tpGather, "gather");
	p.add( showPane, "show");
	this.add(p, BorderLayout.CENTER); 
	initLogGatherGUI();
	initTransGatherGUI();
	this. setVisible(true);
	this.setSize(600,400);
	this. setLocationRelativeTo(null);
	this. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//new updateTable().start();
	//Timer time=new Timer(1000*2*60,new updateTable1());
	//time.start();
	serverIP=Config.getValue("serverIP");
	}
	public void initToolBar() {
	toolBar = new JToolBar();
	getContentPane().add(toolBar, BorderLayout.NORTH);
	btnGather =new JButton("采集数据");
	btnGather.addActionListener(new GatherListener());
	toolBar.add(btnGather);
	btnMatchLog=new JButton("匹配日志数据");
	btnMatchLog.addActionListener(new MatchLogListener());
	toolBar.add(btnMatchLog);
	btnMatchTrans=new JButton("匹配物流数据");
	btnMatchTrans. addActionListener(new MatchTransListener());
	toolBar.add(btnMatchTrans);
	btnSave =new JButton("保存数据");
	btnSave.addActionListener(new SaveDataListener());
	toolBar.add(btnSave);
	btnSend =new JButton("发送数据");
	btnSend.addActionListener(new SendData());
	toolBar.add( btnSend);
	btnShow=new JButton("显示数据");
	btnShow.addActionListener(new ShowDataListener());
	toolBar. add(btnShow);
	
	}
	class GatherListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			card.show(p, "gather");
		}
		
	}
	class MatchLogListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			LogRecAnalyse logAn=new LogRecAnalyse(logList);
			logAn.doFilter();
			matchedLogs=logAn.matchData();
			JOptionPane.showMessageDialog(null, "日志数据过滤分析匹配成功！");
		}
		
	}
	class MatchTransListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			TransportAnalyse transAn=new TransportAnalyse(transList	);
			transAn.doFilter();
			matchedTrans=transAn.matchData();
			JOptionPane.showMessageDialog(null, "订单信息过滤分析匹配成功！");
		}
		
	}
	class SaveDataListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if(matchedLogs!=null&&matchedLogs.size()>0) {
				logRecService.saveMatchLog(matchedLogs);
				//logRecService.saveMatchLogDB(matchedLogs);
				JOptionPane.showMessageDialog(null, "日志数据已保存！");
			}else {
				JOptionPane.showMessageDialog(null, "没有匹配的日志数据！");
			}
			if(matchedTrans!=null&&matchedTrans.size()>0) {
				transportService.saveMatchTransport(matchedTrans);
			//	transportService.saveMatchTransportDB(matchedTrans);
				JOptionPane.showMessageDialog(null, "订单数据已保存！");
			}else {
				JOptionPane.showMessageDialog(null, "没有匹配的订单数据！");
			}
		}
		
	}
	class ShowDataListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			card.show(p, "show");
			showPane.removeAll();
		flushLog();
		flushTrans();
		}
		
	}
	private void flushLog() {
		MatchedTableModel log=new MatchedTableModel(logRecService.readLogResult(), 1);
		JTable logTable=new JTable(log);
		scrollPane=new JScrollPane(logTable);
		showPane.addTab("日志", scrollPane);
	}
	private void flushTrans() {
		MatchedTableModel trans=new MatchedTableModel(transportService.readTranResult(), 0);
		JTable transTable=new JTable(trans);
		scrollPane=new JScrollPane(transTable);
		showPane.addTab("订单", scrollPane);
	}
	 private void initLogGatherGUI() {
		 pLog = new JPanel();
		 tpGather.addTab("日志", pLog);
		 pLog.setLayout(new BoxLayout(pLog,BoxLayout.Y_AXIS));
		 pLogId =new JPanel();
		 pLog. add(pLogId);
		 pLogId.setLayout(new FlowLayout( FlowLayout.CENTER,5,5));
		 lblLogId =new JLabel("日志ID:");
		 pLogId. add(lblLogId);
		 txtLogId =new JTextField();
		 txtLogId. setPreferredSize(new Dimension(100,20));
		 pLogId. add(txtLogId);
		 pName =new JPanel();
		pLog.add(pName);
		pName.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblName=new JLabel("用户ID:");
		pName.add(lblName);
		txtName =new JTextField();
		txtName. setPreferredSize(new Dimension(100,20));
		pName. add( txtName);
		pLocation = new JPanel();
		pLog.add(pLocation);
		lblLocation=new JLabel("登录地点:");
		pLocation. add(lblLocation);
		txtLocation =new JTextField();
		txtLocation. setPreferredSize(new Dimension(100,20));
		pLocation.add(txtLocation);
		pIP = new JPanel();
		pLog.add(pIP);
		lblIP =new JLabel("登录IP:");
		pIP.add(lblIP);
		txtIP= new JTextField();
		txtIP.setPreferredSize(new Dimension(100,20));
		pIP.add(txtIP);
		pLogStatus =new JPanel();
		pLog.add(pLogStatus);
		lblLogStatus =new JLabel("登录状态：");
		pLogStatus.add(lblLogStatus);
		rbLogin= new JRadioButton("登录");
		pLogStatus.add(rbLogin);
		rbLogin.setSelected( true);
		rbLogout = new JRadioButton("登出");
		pLogStatus.add(rbLogout);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbLogin);
		bg.add( rbLogout);
		pLogButton =new JPanel();
		pLog.add( pLogButton);
		btnLogConfirm = new JButton("确认");
		btnLogConfirm. addActionListener(new GatherLogListener());
		pLogButton.add(btnLogConfirm);
		btnLogReset = new JButton("重置");
		btnLogReset.addActionListener(new ResetListener());
		pLogButton.add(btnLogReset);

	 }
	 private void initTransGatherGUI() {
		 pTran = new JPanel();
		 tpGather.addTab("物流",pTran);
		 pTran.setLayout(new BoxLayout(pTran, BoxLayout.Y_AXIS));
		 pTransId = new JPanel();
		 pTran.add(pTransId);
		 lblTransId = new JLabel("订单号：");
		 pTransId.add(lblTransId);
		 txtTransId =new JTextField();
		 txtTransId. setPreferredSize(new Dimension(100,20));
		 pTransId.add(txtTransId);
		 pAdress=new JPanel();
		 pTran. add(pAdress);
		 lblAdress = new JLabel("目的地：");
		 pAdress.add(lblAdress);
		 txtAdress = new JTextField();
		 txtAdress.setPreferredSize(new Dimension(100,20));
		 pAdress.add(txtAdress);
		 pHandler =new JPanel();
		 pTran. add(pHandler);
		 lblHandler = new JLabel("用户ID:");
		 pHandler.add(lblHandler);
		 txtHandler =new JTextField();
		 txtHandler.setPreferredSize(new Dimension(100,20));
		 pHandler.add(txtHandler);
		 pReceiver =new JPanel();
		 pTran.add(pReceiver);
		 lblReceiver = new JLabel("购买数量:");
		pReceiver. add(lblReceiver);
		txtReceiver = new JTextField();
		txtReceiver. setPreferredSize(new Dimension(100,20));
		pReceiver. add(txtReceiver);
		pTranStatus =new JPanel();
		pTran. add(pTranStatus);
		lblTranStatus =new JLabel("产品种类：");
		pTranStatus.add(lblTranStatus);
		String[] tranStatus = new String[]{"农夫山泉","怡宝","百岁山"};
		cmbTanStatus =new JComboBox <String>(tranStatus);
		pTranStatus. add(cmbTanStatus);
		pTranButton = new JPanel();
		pTran.add(pTranButton);
		btnTranConfirm = new JButton("确认");
		btnTranConfirm. addActionListener(new GatherTransListener());
		pTranButton. add(btnTranConfirm);
		btnTranReset = new JButton("重置");
		btnTranReset. addActionListener(new ResetListener());
		pTranButton.add(btnTranReset);
	 }
	 private class GatherLogListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 int id =Integer.parseInt(txtLogId.getText());
			 Date time =new Date();
			 String adress =txtLocation.getText();
			 int type = DataBase.GATHER;
			 String userID = txtName. getText();
			 String ip =txtIP.getText();
			 int logType = rbLogin. isSelected()? LogRec.LOG_IN : LogRec.LOG_OUT;
			 log = new LogRec( id, time, adress, type, userID, ip, logType);
			 logList.add(log);
			 JOptionPane.showMessageDialog(null, "日志信息采集成功");
	 }
	 }
	 private class GatherTransListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
		int id =Integer.parseInt(txtTransId. getText());
		 Date time= new Date();
		 String adress = txtAdress. getText();
		 int type =DataBase. GATHER;
		 String userID = txtHandler. getText();
		 int amount =Integer.parseInt( txtReceiver. getText());
		 int producename=cmbTanStatus. getSelectedIndex()+1;
		 trans = new Transport(id, time, adress, type, userID, amount, 
		 producename);
		 transList. add(trans);
		 JOptionPane. showMessageDialog(null, "物流采集成功!");
		 }
		 
	 }
	 private class ResetListener implements ActionListener{
	 public void actionPerformed(ActionEvent e) {
	 txtName.setText("");
	 txtLocation.setText("");
	 txtIP. setText("");
	 txtAdress.setText("");
	 txtHandler. setText("");
	 txtReceiver. setText("");
	 
	 }
	 }
	 private class updateTable extends Thread{
		 public void run() {
			 while(true) {
				 showPane.removeAll();
				 flushLog();
				 flushTrans();
				 try {
					 Thread.sleep(2*60*1000);//刷新周期两分钟
				 }catch(Exception  e) {
					 e.printStackTrace();
				 }
			 }
		 }
	 }
	 private class updateTable1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			while(true) {
				 showPane.removeAll();
				 flushLog();
				 flushTrans();
				
			 }
		}
		
	 }
	 private class SendData implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			String pat="用户ID：1111 于当地时间：2020-12-28 20:30下单成功！";
			MessageFormat formate=new MessageFormat(pat);
			Locale locale=Locale.getDefault();
			formate.setLocale(locale);
			Object[]ms= {trans.getHandler(),new Date()};
			try {
				if(matchedLogs!=null&&matchedLogs.size()>0) {
					Socket logSocket=new Socket(serverIP,6666);
					ObjectOutputStream logout=new ObjectOutputStream(logSocket.getOutputStream());
					logout.writeObject(matchedLogs);
					logout.flush();
					logout.close();
					matchedLogs.clear();
					JOptionPane. showMessageDialog(null, "日志信息已发送!");
				}else {
					JOptionPane. showMessageDialog(null, "日志信息发送失败!");
				}
				if(matchedTrans!=null&&matchedTrans.size()>0) {
					Socket transsocket=new Socket(serverIP,6668);
					ObjectOutputStream transout=new ObjectOutputStream(transsocket.getOutputStream());
					transout.writeObject(matchedTrans);
					
					transout.flush();
					transout.close();
					matchedTrans.clear();
					String str=formate.format(ms);
					//System.out.println("输出");
					JOptionPane. showMessageDialog(null,str);
				}else {
					JOptionPane. showMessageDialog(null, "订单信息发送失败!");
				}
			}catch(Exception e1) {
				
			}
		}
		 
	 }
}
