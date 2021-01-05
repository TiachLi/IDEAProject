package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class User {
    List myList;
    Set  mySet;
    Map  myMap;

    public List getMyList() {
        return myList;
    }

    public void setMyList(List myList) {
        this.myList = myList;
    }

    public Set getMySet() {
        return mySet;
    }

    public void setMySet(Set mySet) {
        this.mySet = mySet;
    }

    public Map getMyMap() {
        return myMap;
    }

    public void setMyMap(Map myMap) {
        this.myMap = myMap;
    }

    @Override
    public String toString() {
        return "User{" +
                "myList=" + myList +
                ", mySet=" + mySet +
                ", myMap=" + myMap +
                '}';
    }
}
