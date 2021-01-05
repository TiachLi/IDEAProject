package domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 */
public class User implements Serializable {

    private Integer userId;
    private String userNameTest;
    private String userAddressTest;
    private String userSex;
    private Date userBirthday;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userNameTest;
    }

    public void setUserName(String userName) {
        this.userNameTest = userName;
    }

    public String getUserAddress() {
        return userAddressTest;
    }

    public void setUserAddress(String userAddress) {
        this.userAddressTest = userAddress;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday(Date date) {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userNameTest + '\'' +
                ", userAddress='" + userAddressTest + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirthday=" + userBirthday +
                '}';
    }
}
