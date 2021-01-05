package UserGUI;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

import client.TakeOrder;
import entity.*;

public class ackOrder extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField productNumText;
    private JLabel remindLable;
    static public Order order;
    public ackOrder(final Order order) {
        this.order = order;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ackOrder.order.setNum(Integer.parseInt(productNumText.getText()));
                try {
                    boolean isSuccess = new TakeOrder(order).start();
                    if(isSuccess){
                        String[] strings = new String[1];
                        strings[0] = "你成功下单了"+order.getNum()+"瓶"+order.getNum()+"的水,"+"我们会很快帮你安排配送";
                        new YesDialog("你成功下单了"+order.getNum()+"瓶"+order.getProductName()+"的水,"+"我们会很快帮你安排配送").main(strings);
                    }
                    System.out.println(isSuccess);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // 单击十字时调用 onCancel()
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // 遇到 ESCAPE 时调用 onCancel()
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // 在此处添加代码
        dispose();
    }

    private void onCancel() {
        // 必要时在此处添加您的代码
        dispose();
    }

    public static void main(String[] args) {
        ackOrder dialog = new ackOrder(order);
        dialog.pack();
        dialog.setLocation(500,500);
        dialog.setVisible(true);
    }
}
