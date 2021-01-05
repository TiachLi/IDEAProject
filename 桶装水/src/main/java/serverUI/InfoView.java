package serverUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import dao.impl.OrderDaoImpl;
import dao.impl.ProductDaoImpl;
import entity.*;

public class InfoView implements ActionListener {
    private JTable infoTable;
    private JPanel InfoPanel;
    private JButton ProductButton;
    private JButton OrderButton;
    private JToolBar ServerMenu;
    private JScrollPane tableJScrollPane;
    private JButton updateButton;
    private JButton plusbutton;
    private JButton 开始Button;
    OrderDaoImpl orderDao = new OrderDaoImpl();
    ProductDaoImpl productDao = new ProductDaoImpl();
    static public List<Order> orders;
    static public List<Product> products;
    static Vector<Integer> updateRow = new Vector<Integer>();
    boolean Select = false;
    boolean isInsert = false;
    static Product newProduct;
    static Timer timer;
    public InfoView() {
        orderDao.getConnection();
        productDao.getConnection();
        orders = orderDao.getAll();
        products = productDao.getAll();
        ProductButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                infoTable.setModel(new ServerProductTableModel(products));
            }
        });
        OrderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                infoTable.setModel(new SeverOrderTableModel(orders));
                Select = true;
            }
        });
        infoTable.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {

            }
        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Product product = new Product();
                System.out.println(updateRow.size());
                String value[] = new String[5];
                for (int row: updateRow){
                    for (int i = 0; i < 5 ; i++){
                        value[i] = (String) infoTable.getModel().getValueAt(row,i);
                    }
                    product.setpID(value[0]);
                    product.setpName(value[1]);
                    product.setPrice(Integer.parseInt(value[2]));
                    product.setSales(Integer.parseInt(value[3]));
                    product.setAllowance(Integer.parseInt(value[4]));
                    productDao.getConnection();
                    productDao.updateProduct(product);
                }
                if (isInsert){
                    productDao.insertProduct(product);
                }
            }
        });

        Action updateTableAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("定时刷新");
                if (Select){
                    orderDao.getConnection();
                    orders = orderDao.getAll();
                    infoTable.setModel(new SeverOrderTableModel(orders));
                }else {
                    productDao.getConnection();
                    infoTable.setModel(new ServerProductTableModel(products));
                    products = productDao.getAll();
                }
                infoTable.validate();
                infoTable.updateUI();
            }
        };
        timer = new Timer(1000,updateTableAction);
        //timer.start();
        plusbutton.addActionListener(this);
        开始Button.addActionListener(this);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("InfoView");
        frame.setLocation(400,400);
        frame.setSize(400,400);
        frame.setContentPane(new InfoView().InfoPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ProductDaoImpl productDao = new ProductDaoImpl();
        productDao.getConnection();
        List<Product> products;
        products = productDao.getAll();
        infoTable = new JTable(new ServerProductTableModel(products));
        infoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = e.getLastIndex();
                boolean has = false;
                for (int i: updateRow){
                    if (i == row){
                        has = true;
                        break;
                    }
                }
                if (!has)
                updateRow.add(row);
                Product product;
                String value[] = new String[5];
                for (int i = 0; i < 5 ; i++){
                    value[i] = (String) infoTable.getModel().getValueAt(row,i);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == plusbutton){
            timer.stop();
            products.add(new Product());
            infoTable.setModel(new ServerProductTableModel(products));
            isInsert = true;
        } else if (e.getSource() == 开始Button){
            timer.start();
        }
    }
}
