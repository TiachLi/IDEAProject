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
		setTitle("�û�id");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�û�id");
		lblNewLabel.setBounds(80, 74, 72, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("����");
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
		
		button = new JButton("��¼");
		button.setBounds(52, 179, 113, 27);
		getContentPane().add(button);
		
		button_1 = new JButton("���");
		button_1.setBounds(177, 179, 113, 27);
		getContentPane().add(button_1);
		
		button_2 = new JButton("ע��");
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
		// TODO �Զ����ɵķ������
		user=userService.findUserByName(textField.getText());
		if(user!=null) {
			if(user.getpassword().equals(new String(textField_1.getPassword()))) {
				JOptionPane.showMessageDialog(null, "��½�ɹ���");
				LoginFrame.this.setVisible(false);
				new MainFrame();
			}
			else {
				JOptionPane.showMessageDialog(null, "�������");
			}
		}else {
			JOptionPane.showMessageDialog(null, "�û�ID�����ڣ�");
		}
	}
	
}
public class Reset implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		textField.setText("");
		textField_1.setText("");
	}
	
}
public class regist implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		new RegistFrame();
	}
	
}
}
