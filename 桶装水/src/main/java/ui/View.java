package ui;

import javax.swing.*;

public class View {

    private JPanel MainPane;

    public static void main(String[] args) {
        JFrame frame = new JFrame("View");
        frame.setContentPane(new View().MainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JTable orderTable;
    private JToolBar menu;
    private JScrollPane tableJscrollPane;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        orderTable = new JTable(new MyTableModel());
    }
}
