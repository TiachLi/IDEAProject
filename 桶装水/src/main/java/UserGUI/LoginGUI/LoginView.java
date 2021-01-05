package UserGUI.LoginGUI;

import client.Client;
import UserGUI.RegisterGUI.RegisterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import entity.*;
import UserGUI.UserView;

public class LoginView {
    private JPanel Login;
    private JPasswordField PasswordField;
    private JButton LoginButton;
    private JButton registerButton;
    private JTextField AccountField;
    private JLabel Account;
    private JLabel Password;
    static JFrame frame;
    public LoginView() {
        LoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("登录");
                User user = new User();
                boolean isSuccess = false;
                user.setID(AccountField.getText());
                user.setPSW(PasswordField.getText());
                Client client = new Client();
                try {
                    isSuccess = client.Client(user);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if (isSuccess){
                    frame.dispose();
                    new UserView(client.getProducts(),client.getOrder(),client.getUser()).main(null);
                }
            }
        });
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("注册");
                new RegisterView().main(null);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("LoginView");
        frame.setPreferredSize(new Dimension(400,400));
        frame.setLocation(200,200);
        frame.setContentPane(new LoginView().Login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
