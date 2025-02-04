package Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

public class JedisUtils {
    private static JedisPool jedisPool;
    static {
        Properties pro =new Properties();
        try {
            pro.load(JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties"));
            //获取数据，设置到JedisPoolConfig中
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
            config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

            jedisPool=new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}
