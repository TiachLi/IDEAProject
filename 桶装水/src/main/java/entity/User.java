package entity;

import java.io.Serializable;

public class User implements Serializable {
    String ID;
    String PSW;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPSW() {
        return PSW;
    }

    public void setPSW(String PSW) {
        this.PSW = PSW;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
                ", PSW='" + PSW + '\'' +
                '}';
    }
}
