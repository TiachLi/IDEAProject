package net;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Service.LogRecService;
import Service.TransportService;
import entity.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NetServers extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new NetServers();
	}

	/**
	 * Create the frame.
	 */
	public NetServers() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u670D\u52A1\u5668\u7AEF");
		lblNewLabel.setFont(new Font("华文行楷", Font.BOLD, 30));
		lblNewLabel.setBounds(148, 30, 139, 41);
		getContentPane().add(lblNewLabel);
		
		JButton button = new JButton("\u4EA7\u54C1\u5904\u7406");
		button.setBounds(148, 84, 113, 27);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8BA2\u5355\u5904\u7406");
		button_1.setBounds(148, 139, 113, 27);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u5F00\u542F\u670D\u52A1\u5668");
		button_2.setBounds(148, 197, 113, 27);
		getContentPane().add(button_2);
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AcceptLog(6666).start();
				new AccepTrans(6668).start();
				JOptionPane.showMessageDialog(null, "服务器已开启！");
			}
		});
	}
	private class AcceptLog extends Thread{
		private ServerSocket serverScoket;
		private Socket scoket;
		private LogRecService logRec;
		private ObjectInputStream osi;
		public AcceptLog(int port) {
			logRec=new LogRecService();
			try {
				serverScoket=new ServerSocket(port);
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		public void run(){
				while(this.isAlive()) {
					try {
						scoket=serverScoket.accept();
						if(scoket!=null) {
							osi=new ObjectInputStream(scoket.getInputStream());
							ArrayList<MatchedLogRec>matchedLogRec=(ArrayList<MatchedLogRec>)osi.readObject();
							logRec.saveMatchLogDB(matchedLogRec);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				try {
					osi.close();
					scoket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
	}
	private class AccepTrans extends Thread{
		private ServerSocket serverScoket;
		private Socket scoket;
		private TransportService transportService;
		private ObjectInputStream osi;
		public AccepTrans(int port) {
			transportService=new TransportService();
			try {
				serverScoket=new ServerSocket(port);
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		public void run(){
				while(this.isAlive()) {
					try {
						scoket=serverScoket.accept();
						if(scoket!=null) {
							osi=new ObjectInputStream(scoket.getInputStream());
							ArrayList<MatchedTransport>matchedTransRec=(ArrayList<MatchedTransport>)osi.readObject();
							transportService.saveMatchTransportDB(matchedTransRec);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				try {
					osi.close();
					scoket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
	}
}
