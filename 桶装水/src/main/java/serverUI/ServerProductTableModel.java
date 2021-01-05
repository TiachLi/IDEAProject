package serverUI;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;
import entity.*;
public class ServerProductTableModel extends AbstractTableModel {
    String[] columnNames = new String[]{"产品编号", "产品名字", "产品价格", "销量", "余量"};
    Vector<Vector<String>> data = new Vector<Vector<String>>();
    List<Product> products;
    public int getRowCount() {
        return data.size();
    }
    public ServerProductTableModel(List<Product> products){
        this.products = products;
        for (Product product:products){
            data.add(new Vector<String>());
            data.get(data.size()-1).add(product.getpID());
            data.get(data.size()-1).add(product.getpName());
            data.get(data.size()-1).add(String.valueOf(product.getPrice()));
            data.get(data.size()-1).add(String.valueOf(product.getSales()));
            data.get(data.size()-1).add(String.valueOf(product.getAllowance()));
        }
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
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex).set(columnIndex,(String) aValue);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
