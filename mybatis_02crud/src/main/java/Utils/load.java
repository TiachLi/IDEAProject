package Utils;

import java.io.InputStream;

public class load {
    public static InputStream getAsInputStream(){
        return load.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
    }
}
