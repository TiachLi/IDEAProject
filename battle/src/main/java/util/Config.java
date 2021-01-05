package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.Properties;
public class Config {
	private static Properties p=null;
	static {
		try {
			p=new Properties();
			InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("SQLserve.properties");
			p.load(systemResourceAsStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return p.get(key).toString();
	}
}
