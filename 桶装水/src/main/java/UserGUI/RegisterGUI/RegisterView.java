package UserGUI.RegisterGUI;

import client.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import entity.*;
public class RegisterView {
    private JPanel RegisterPanel;
    private JTextField Phone;
    private JPasswordField password;
    private JTextField Account;
    private JButton OkButton;
    private JPanel Empty1;
    private JLabel PhoneLable;
    private JLabel AccountLable;
    private JLabel PasswordLable;
    static private JFrame frame;
    public RegisterView() {
        OkButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    User user = new User();
                    user.setID(Account.getText());

                    user.setPSW(Account.getText());
                    Register register = new Register(user);
                    boolean isSuccess = register.start();
                    if (isSuccess){
                        frame.dispose();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("RegisterView");
        frame.setLocation(200,200);
        frame.setPreferredSize(new Dimension(400,400));
        frame.setContentPane(new RegisterView().RegisterPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
