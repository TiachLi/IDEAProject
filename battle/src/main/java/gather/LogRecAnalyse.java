package gather;

import java.util.ArrayList;


import gather.*;

import entity.*;

import exception.DataAnalyseException;

public class LogRecAnalyse extends DataFilter implements IDataAnalyse {
	// ����¼������
		private ArrayList<LogRec> logIns = new ArrayList<>();
		// ���ǳ�������
		private ArrayList<LogRec> logOuts = new ArrayList<>();

		// ���췽��
		public LogRecAnalyse() {
		}

		public LogRecAnalyse(ArrayList<LogRec> logRecs) {
			super(logRecs);
		}

		// ʵ��DataFilter�������еĹ��˳��󷽷�
		public void doFilter() {
			// ��ȡ���ݼ���
			ArrayList<LogRec> logs = (ArrayList<LogRec>) this.getDatas();

			// ����������־���ݽ��й��ˣ�������־��¼״̬�ֱ���ڲ�ͬ��������
			for (LogRec rec : logs) {
				if (rec.getLogType() == LogRec.LOG_IN) {
					// ��ӵ�����¼����־������
					logIns.add(rec);
				} else if (rec.getLogType() == LogRec.LOG_OUT) {
					// ��ӵ����ǳ�����־������
					logOuts.add(rec);
				}
			}

		}

		// ʵ��IDataAnalyse�ӿ������ݷ�������
		public ArrayList<MatchedLogRec> matchData() {
			// ������־ƥ�伯��
			ArrayList<MatchedLogRec> matchLogs = new ArrayList<>();

			// ����ƥ�����
			for (LogRec in : logIns) {
				for (LogRec out : logOuts) {
					if ((in.getUser().equals(out.getUser()))
							&& (in.getIp().equals(out.getIp()))) {
						// �޸�in��out��־״̬����Ϊ��ƥ�䡱
						in.setType(DataBase.MATHCH);
						out.setType(DataBase.MATHCH);
						// ��ӵ�ƥ�伯����
						matchLogs.add(new MatchedLogRec(in, out));
					}
				}
			}
			try {
				if (matchLogs.size() == 0) {
					// û�ҵ�ƥ�������,�׳�DataAnalyseException�쳣
					throw new DataAnalyseException("û��ƥ�����־���ݣ�");
				}
			} catch (DataAnalyseException e) {
				e.printStackTrace();
			}
			return matchLogs;
		}
}
