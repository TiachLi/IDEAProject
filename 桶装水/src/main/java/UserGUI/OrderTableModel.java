package UserGUI;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;
import entity.*;
public class OrderTableModel extends AbstractTableModel {
    String[] columnNames = new String[]{"订单号", "产品名字", "数量", "价格","下单时间"};
    Vector<Vector<String>> data = new Vector<Vector<String>>();
    List<Order> orders;
    public OrderTableModel(List<Order> orders){
        this.orders = orders;
        for (Order order:orders){
            data.add(new Vector<String>());
            data.get(data.size()-1).add(order.getOrderID());
            data.get(data.size()-1).add(order.getProductName());
            data.get(data.size()-1).add(String.valueOf(order.getNum()));
            data.get(data.size()-1).add(String.valueOf(order.getAllprice()));
            data.get(data.size()-1).add(order.getDate());
        }
    }

    public void setData(List<Order> orders) {
        this.orders = orders;
        for (int i = 0; i < orders.size();i++){
            data.get(i).set(0,orders.get(i).getpID());
            data.get(i).set(0,orders.get(i).getProductName());
            data.get(i).set(0,String.valueOf(orders.get(i).getNum()));
            data.get(i).set(0,String.valueOf(orders.get(i).getAllprice()));
            data.get(i).set(0,orders.get(i).getDate());
        }
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
    public String getColumnName(int column) {
        return columnNames[column];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
