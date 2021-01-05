package com.example.springboot_cache;

import com.example.springboot_cache.bean.Employee;
import com.example.springboot_cache.controller.EmployeeController;
import com.example.springboot_cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootCacheApplicationTests {
    @Resource
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串的

    @Resource
    RedisTemplate redisTemplate;  //k-v都是对象的

    @Autowired
   // @Qualifier("empRedisTemplate")
    RedisTemplate empRedisTemplate;
    @Test
    void contextLoads() {
        //给redis中保存数据
        stringRedisTemplate.opsForValue().append("msg","hello");
		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);

		stringRedisTemplate.opsForList().leftPush("mylist","1");
		stringRedisTemplate.opsForList().leftPush("mylist","2");
    }

    //测试保存对象
    @Test
    public void test02(){
        Employee empById = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        redisTemplate.opsForValue().set("emp-01",empById);
/*        1、将数据以json的方式保存
        (1)自己将对象转为json
        (2)redisTemplate默认的序列化规则；改变默认的序列化规则；*/
        empRedisTemplate.opsForValue().set("emp-01",empById);
    }
    @Test
    public void test03() {
        stringRedisTemplate.opsForList().remove("mylist",-2,"1");
        stringRedisTemplate.opsForList().remove("mylist",-2,"2");
    }
    @Resource
    EmployeeController employeeController;

    @Test
    public void test4(){
        employeeController.getEmployee(1);
    }

}
