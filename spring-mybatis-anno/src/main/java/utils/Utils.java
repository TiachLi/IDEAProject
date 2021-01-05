package main.java.utils;

import java.io.InputStream;

public class Utils {
    public static InputStream load(){
        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream("SqlConfig.xml");

        return inputStream;
    }
}
