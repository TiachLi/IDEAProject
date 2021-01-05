package UserGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import client.Client;
import entity.*;

public class UserView {
    private JTable usertTable;
    private JPanel mainPanel;
    private JToolBar userMenu;
    private JScrollPane tableJSrollPane;
    private JButton ProductButton;
    private JButton OrderButton;
    static List<Product> products;
    static List<Order> orders;
    static User user;
    boolean Select = false;
    public UserView(List<Product> products, List<Order> orders , final User user){
        this.user = user;
        this.products = products;
        this.orders = orders;
        ProductButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usertTable.setModel(new ProductTableModel(UserView.products));
                Select = true;
            }
        });
        OrderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usertTable.setModel(new OrderTableModel(UserView.orders));
                Select = false;
            }
        });
        Action updateTableAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client();
                try {
                    client.Client(UserView.user);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                UserView.products = client.getProducts();
                UserView.orders = client.getOrder();
                if (!Select){
                    usertTable.setModel(new OrderTableModel(UserView.orders));
                }else {
                    usertTable.setModel(new ProductTableModel(UserView.products));
                }
                System.out.println("1");
                usertTable.validate();
                usertTable.updateUI();
            }
        };
        Timer timer = new Timer(5000,updateTableAction);
        timer.start();
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("UserView");
        frame.setContentPane(new UserView(products,orders,user).mainPanel);
        frame.setLocation(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        usertTable = new JTable(new ProductTableModel(products));
        usertTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && usertTable.getColumnCount() == 5){
                    Order order = new Order();
                    int row = usertTable.getSelectedRow();
                    order.setpID((String) usertTable.getValueAt(row,0));
                    order.setuID(user.getID());
                    new ackOrder(order).main(null);
                }
            }
        });
    }
}
