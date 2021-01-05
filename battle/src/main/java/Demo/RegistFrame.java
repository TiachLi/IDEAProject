package Demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import db.DButil;
import Service.UserService;
import entity.User;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class RegistFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;
	private JPasswordField textField_2;
	private JTextField textField_3;
	JLabel label,label_1,label_2,label_3,label_4,lblid,lblNewLabel;
	JButton button,button_1,btnid;
	JRadioButton radioButton,radioButton_1 ;
	
	UserService uservice=new UserService();

	public RegistFrame() {
		this.setVisible(true);
		setTitle("\u7528\u6237\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		
		label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(24, 53, 72, 18);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(98, 50, 256, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_1.setBounds(24, 95, 72, 18);
		getContentPane().add(label_1);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(98, 91, 256, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_2.setBounds(24, 137, 80, 18);
		getContentPane().add(label_2);
		
		textField_2 = new JPasswordField();
		textField_2.setBounds(98, 137, 256, 24);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		label_3 = new JLabel("\u6027  \u522B\uFF1A");
		label_3.setBounds(27, 187, 72, 18);
		getContentPane().add(label_3);
		
		 radioButton = new JRadioButton("\u7537");
		radioButton.setBounds(92, 182, 53, 27);
		getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("\u5973");
		radioButton_1.setBounds(151, 182, 53, 27);
		getContentPane().add(radioButton_1);
		 ButtonGroup bg=new ButtonGroup();
		 bg.add(radioButton);
		 bg.add(radioButton_1);
		 
		 label_4 = new JLabel("\u7535  \u8BDD\uFF1A");
		 label_4.setBounds(28, 228, 72, 18);
		 getContentPane().add(label_4);
		 
		 textField_3 = new JTextField();
		 textField_3.setBounds(98, 225, 256, 24);
		 getContentPane().add(textField_3);
		 textField_3.setColumns(10);
		 
		  button = new JButton("\u786E\u8BA4");
		 button.setBounds(52, 263, 113, 27);
		 getContentPane().add(button);
		 
		  button_1 = new JButton("\u91CD\u7F6E");
		 button_1.setBounds(186, 263, 113, 27);
		 getContentPane().add(button_1);
		 
		 lblid = new JLabel("\u7528\u6237ID\uFF1A");
		 lblid.setBounds(24, 18, 72, 18);
		 getContentPane().add(lblid);
		 
		 lblNewLabel = new JLabel(" ");
		 lblNewLabel.setBounds(97, 16, 72, 18);
		 getContentPane().add(lblNewLabel);
		 
		 btnid = new JButton("\u66F4\u6362ID");
		 btnid.setBounds(199, 10, 113, 27);
		 getContentPane().add(btnid);
		 btnid.addActionListener(new ActionListener() {
			 Random random;
				int i=1;
			@Override
			public void actionPerformed(ActionEvent e) {
				String str=(int)((Math.random()*9+1)*100000)+"";
				DButil butil=new DButil();
				Connection con=butil.getConnection();
				try {
					Statement sql=con.createStatement();
					ResultSet rs=sql.executeQuery("select * from [dbo].[User]");
					while(rs.next()) {
						if(rs.getString(1).equals(str))
							i=0;
					}
					if(i!=0)
						lblNewLabel.setText(str);
				}catch(SQLException e1) {
					
				}
			}
		});
		ActionListener listener=new Register();
		button.addActionListener(listener);
		ActionListener listener1=new Reset();
		button_1.addActionListener(listener1);
	}
	private class Register implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String userID=lblNewLabel.getText();
		String name=textField.getText();
		String password=new String(textField_1.getPassword());
		String repassword=new String(textField_2.getPassword());
		int x=Integer.parseInt(radioButton.isSelected()?"0":"1");
		String sex=null;
		if(x==0)
			sex="男";
		else
			sex="女";
		String phone=textField_3.getText();
		String tel = "^((1[0-9]))\\d{9}$";// 手机号格式
	      Pattern pattern = Pattern.compile(tel);
	      Matcher matcher = pattern.matcher(phone);
		if(matcher.matches()) {
			if(password.equals(repassword)&&userID.length()==6) {
			User user=new User(userID,name,password,sex,phone);
			if(uservice.saveUser(user))
				JOptionPane.showMessageDialog(null, "注册成功！");
			else
				JOptionPane.showMessageDialog(null, "注册失败！");
		}
		else {
			JOptionPane.showMessageDialog(null, "密码不一致");
		}
		}
		else {
			JOptionPane.showMessageDialog(null, "电话号格式错误");
		}
		
	}
	
}
	public class Reset implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			
			radioButton.setSelected(false);
			radioButton_1.setSelected(false);
			textField_3.setText("");
		}
		
	}
}
