package Service.imp;

import Service.ProvinceService;
import Utils.JedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProvinceDaoService;
import dao.imp.ProvinceDaoServiceImp;
import domain.Province;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class ProvinceServiceImp implements ProvinceService {
    private ProvinceDaoService dao = new ProvinceDaoServiceImp();


    @Override
    public String findAllJSON() {
        //1.先从redis中查询数据
        //1.1获取redis客户端连接对象
        Jedis jedis = JedisUtils.getJedis();
        String province_json = jedis.get("province");
        if(province_json == null || province_json.length() == 0){
            //redis中没有数据
            System.out.println("redis中没数据，查询数据库...");
            //2.1从数据中查询
            List<Province> ps = dao.findAll();
            //2.2将list序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(ps);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            //2.3 将json数据存入redis
            jedis.set("province",province_json);
            //归还连接
            jedis.close();

        }else{
            System.out.println("redis中有数据，查询缓存...");
        }

        return province_json;
    }
}
