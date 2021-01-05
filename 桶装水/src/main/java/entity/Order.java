package entity;

import java.io.Serializable;

public class Order implements Serializable {
    String orderID;
    String pID;
    String uID;
    int num;
    int allprice;
    String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getpID() {
        return pID;
    }

    public String getuID() {
        return uID;
    }

    public int getNum() {
        return num;
    }

    public int getAllprice() {
        return allprice;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setAllprice(int allprice) {
        this.allprice = allprice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", pID='" + pID + '\'' +
                ", uID='" + uID + '\'' +
                ", num=" + num +
                ", allprice=" + allprice +
                '}';
    }
}
