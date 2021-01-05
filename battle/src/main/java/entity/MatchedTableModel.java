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
	//����
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		try {
			rs.last();
			return rs.getRow();
		}catch(Exception e) {
			return 0;
		}
		
	}
//����
	@Override
	public int getColumnCount() {
		// TODO �Զ����ɵķ������
		try {
			return rsmd.getColumnCount();
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO �Զ����ɵķ������
		try {
			rs.absolute(rowIndex+1);
			return rs.getObject(columnIndex+1);
		}catch(Exception e) {
			return null;
		}
	}
	public String getColumnName(int column) {
		String[] logArray= {
				"��־ID","�ɼ�ʱ��","�ɼ��ص�","״̬","�û�ID","IP","��־����"
		};
		String[] tranArray={
			"������","�ɼ�ʱ��","�ջ���ַ","״̬","�û�ID","��������","��Ʒ����"
		};
		return sign==1?logArray[column]:tranArray[column];
	}

}
