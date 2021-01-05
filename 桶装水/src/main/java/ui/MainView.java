package ui;

import javax.swing.*;

public class MainView extends JFrame {
    private JTable table;
    private JScrollPane tableScrollPane;
    private JToolBar menu;
    private JPanel menuPanel;

    MainView(){
        createUIComponents();
        setSize(500,500);
        add(menu);
        add(tableScrollPane);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.getContentPane();
        mainView.show();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        menu = new JToolBar();
        table = new JTable(new MyTableModel());
        tableScrollPane = new JScrollPane(table);
    }
}
