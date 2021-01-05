package com.domain;

public class House {
    int houseId;
    String houseName;
    String housePrice;
    String houseAddress;
    String houseType;
    String houseTel;
    String subTel;
    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(String housePrice) {
        this.housePrice = housePrice;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseTel() {
        return houseTel;
    }

    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }



    public String getSubTel() {
        return subTel;
    }

    public void setSubTel(String subTel) {
        this.subTel = subTel;
    }

    @Override
    public String toString() {
        return "House{" +
                "houseId=" + houseId +
                ", houseName='" + houseName + '\'' +
                ", housePrice='" + housePrice + '\'' +
                ", houseAddress='" + houseAddress + '\'' +
                ", houseType='" + houseType + '\'' +
                ", houseTel='" + houseTel + '\'' +
                ", subTel='" + subTel + '\'' +
                '}';
    }
}
