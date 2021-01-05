package Demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Service.UserService;
import entity.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;
    User user;
    UserService userService=new UserService();
    JButton button,button_1,button_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("用户id");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户id");
		lblNewLabel.setBounds(80, 74, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(80, 129, 72, 18);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(151, 70, 148, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(151, 125, 148, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		button = new JButton("登录");
		button.setBounds(52, 179, 113, 27);
		getContentPane().add(button);
		
		button_1 = new JButton("清空");
		button_1.setBounds(177, 179, 113, 27);
		getContentPane().add(button_1);
		
		button_2 = new JButton("注册");
		button_2.setBounds(305, 179, 113, 27);
		getContentPane().add(button_2);
		ActionListener listen,listen1,listen2;
		listen=new Login();
		button.addActionListener(listen);
		listen1=new Reset();
		button_1.addActionListener(listen1);
		listen2=new regist();
		button_2.addActionListener(listen2);
	}
public class Login implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		user=userService.findUserByName(textField.getText());
		if(user!=null) {
			if(user.getpassword().equals(new String(textField_1.getPassword()))) {
				JOptionPane.showMessageDialog(null, "登陆成功！");
				LoginFrame.this.setVisible(false);
				new MainFrame();
			}
			else {
				JOptionPane.showMessageDialog(null, "密码错误！");
			}
		}else {
			JOptionPane.showMessageDialog(null, "用户ID不存在！");
		}
	}
	
}
public class Reset implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		textField.setText("");
		textField_1.setText("");
	}
	
}
public class regist implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		new RegistFrame();
	}
	
}
}
