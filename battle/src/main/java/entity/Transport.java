package entity;

import java.io.Serializable;
import java.util.Date;

public class Transport extends DataBase implements Serializable{
	/**
	 * �ͻ�ID
	 */
	private String UserID;
	/**
	 * ��������
	 */
	private int amount;
	/**
	 * ��������
	 */
	private int producename;
	/**
	 * ����״̬����:������, �ͻ���, ��ǩ��
	 */
	public static final int SENDDING = 1;// ũ��ɽȪ
	public static final int TRANSPORTING = 2;// ����
	public static final int RECIEVED = 3;// ����ɽ

	public String getHandler() {
		return UserID;
	}

	public void setHandler(String UserID) {
		this.UserID = UserID;
	}

	public int getReciver() {
		return amount;
	}

	public void setReciver(int amount) {
		this.amount = amount;
	}

	public int getTransportType() {
		return producename;
	}

	public void setTransportType(int transportType) {
		this.producename = transportType;
	}

	public Transport() {

	}

	public Transport(int id, Date time, String address, int type,
			String UserID, int amount, int producename) {
		super(id, time, address, type);
		this.UserID = UserID;
		this.amount = amount;
		this.producename = producename;
	}

	public String toString() {
		return this.getId() + "," + this.getTime() + "," + this.getAddress()
				+ "," + this.getType() + "," + UserID + ","+amount+"," +producename;
	}

}
