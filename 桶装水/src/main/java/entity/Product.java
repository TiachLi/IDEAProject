package entity;

import java.io.Serializable;

public class Product implements Serializable {
    String pID;
    String pName;
    int Price;
    int sales;
    int allowance;

    public String getpID() {
        return pID;
    }

    public String getpName() {
        return pName;
    }

    public int getPrice() {
        return Price;
    }

    public int getSales() {
        return sales;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pID='" + pID + '\'' +
                ", pName='" + pName + '\'' +
                ", Price=" + Price +
                ", sales=" + sales +
                ", allowance=" + allowance +
                '}';
    }
}
