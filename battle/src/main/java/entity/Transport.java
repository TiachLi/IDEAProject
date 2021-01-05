package entity;

import java.io.Serializable;
import java.util.Date;

public class Transport extends DataBase implements Serializable{
	/**
	 * 客户ID
	 */
	private String UserID;
	/**
	 * 货物数量
	 */
	private int amount;
	/**
	 * 货物种类
	 */
	private int producename;
	/**
	 * 物流状态常量:发货中, 送货中, 已签收
	 */
	public static final int SENDDING = 1;// 农夫山泉
	public static final int TRANSPORTING = 2;// 怡宝
	public static final int RECIEVED = 3;// 百岁山

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
