package entity;
import java.sql.*;

import javax.swing.table.AbstractTableModel;
public class MatchedTableModel extends AbstractTableModel{
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private int sign;

	public MatchedTableModel(ResultSet rs,int sign) {
		this.rs=rs;
		this.sign=sign;
		try {
			rsmd=rs.getMetaData();
		}catch(Exception e) {
			rsmd=null;
		}
	}
	//行数
	public int getRowCount() {
		// TODO 自动生成的方法存根
		try {
			rs.last();
			return rs.getRow();
		}catch(Exception e) {
			return 0;
		}
		
	}
//列数
	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		try {
			return rsmd.getColumnCount();
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		try {
			rs.absolute(rowIndex+1);
			return rs.getObject(columnIndex+1);
		}catch(Exception e) {
			return null;
		}
	}
	public String getColumnName(int column) {
		String[] logArray= {
				"日志ID","采集时间","采集地点","状态","用户ID","IP","日志类型"
		};
		String[] tranArray={
			"订单号","采集时间","收货地址","状态","用户ID","购买数量","产品种类"
		};
		return sign==1?logArray[column]:tranArray[column];
	}

}
