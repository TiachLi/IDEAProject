package config;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Config{
    private static Properties p = null;
    static {
        try {
            p = new Properties();
            p.load(new FileInputStream("src/Config/jdbc.properties"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String getValue(String key){
        String value = null;
        try {
            value = new String(p.get(key).toString().getBytes("ISO-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            return value;
        }
    }
}
