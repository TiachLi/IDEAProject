package com.domain;

public class Admin {
    private int adminId;
    private String adminName;
    private String adminPassword;
    private String adminEmail;
    private String adminTel;
    private String adminGender;
    private String adminBirthday;
    private String adminRealName;

    public String getAdminRealName() {
        return adminRealName;
    }

    public void setAdminRealName(String adminRealName) {
        this.adminRealName = adminRealName;
    }

    public int getAId() {
        return adminId;
    }

    public void setAId(int aId) {
        this.adminId = aId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel;
    }

    public String getAdminGender() {
        return adminGender;
    }

    public void setAdminGender(String adminGender) {
        this.adminGender = adminGender;
    }

    public String getAdminBirthday() {
        return adminBirthday;
    }

    public void setAdminBirthday(String adminBirthday) {
        this.adminBirthday = adminBirthday;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminTel='" + adminTel + '\'' +
                ", adminGender='" + adminGender + '\'' +
                ", adminBirthday='" + adminBirthday + '\'' +
                '}';
    }
}
