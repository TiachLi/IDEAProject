package gather;

import java.util.ArrayList;

import entity.DataBase;

public abstract class DataFilter {
	// ���ݼ���,ʹ�÷��ͼ���
		private ArrayList<? extends DataBase> datas;

		public ArrayList<? extends DataBase> getDatas() {
			return datas;
		}

		public void setDatas(ArrayList<? extends DataBase> datas) {
			this.datas = datas;
		}

		// ���췽��
		public DataFilter() {

		}

		public DataFilter(ArrayList<? extends DataBase> datas) {
			this.datas = datas;
		}

		// ���ݹ��˳��󷽷�
		public abstract void doFilter();
}
