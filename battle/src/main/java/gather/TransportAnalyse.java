package gather;

import java.util.ArrayList;

import gather.*;
import entity.*;
import exception.DataAnalyseException;

public class TransportAnalyse extends DataFilter implements IDataAnalyse{
	// ���Ｏ��
		private ArrayList<Transport> transSends = new ArrayList<>();


		// ���췽��
		public TransportAnalyse() {
		}

		public TransportAnalyse(ArrayList<Transport> trans) {
			super(trans);
		}

		// ʵ��DataFilter�������еĹ��˳��󷽷�
		public void doFilter() {
			// ��ȡ���ݼ���
			ArrayList<Transport> trans = (ArrayList<Transport>) this.getDatas();

			// ���������������ݽ��й��ˣ���������״̬�ֱ���ڲ�ͬ�ļ�����
			for (Transport tran : trans) {
				if (tran.getTransportType() == Transport.SENDDING||tran.getTransportType() == Transport.TRANSPORTING
						||tran.getTransportType() == Transport.RECIEVED) {
					transSends.add(tran);
				} 
			}

		}

		// ʵ��IDataAnalyse�ӿ������ݷ�������
		public ArrayList<MatchedTransport> matchData() {
			// ��������ƥ�伯��
			ArrayList<MatchedTransport> matchTrans = new ArrayList<>();
			// ����ƥ�����
			for (Transport send : transSends) {
							// �޸�����״̬����Ϊ��ƥ�䡱
							send.setType(DataBase.MATHCH);
							// ��ӵ�ƥ�伯����
							matchTrans.add(new MatchedTransport(send));
												
			}
			try {
				if (matchTrans.size() == 0) {
					// û�ҵ�ƥ�������,�׳�DataAnalyseException�쳣
					throw new DataAnalyseException("û��ƥ����������ݣ�");
				}
			} catch (DataAnalyseException e) {
				e.printStackTrace();
			}
			return matchTrans;
		}
}
