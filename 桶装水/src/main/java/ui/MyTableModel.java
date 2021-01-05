package ui;

import dao.impl.OrderDaoImpl;

import javax.swing.table.AbstractTableModel ;
import java.util.List;
import java.util.Vector;

import entity.Order;
public class MyTableModel extends AbstractTableModel {
    OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
    String[] columnNames = new String[]
            { "产品编号", "产品名字", "产品价格", "销量", "余量" };
    Vector<Vector<String>> data = new Vector<Vector<String>>();
    public MyTableModel(){
        orderDaoImpl.getConnection();
        List<Order> orderList = orderDaoImpl.getAll();
        for (Order order:orderList)
        {
            Vector<String> vector = new Vector<String>();
            vector.add(order.getOrderID());
            vector.add(order.getpID());
            vector.add(order.getuID());
            vector.add(String.valueOf(order.getAllprice()));
            vector.add(String.valueOf(order.getNum()));
            data.add(vector);
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
