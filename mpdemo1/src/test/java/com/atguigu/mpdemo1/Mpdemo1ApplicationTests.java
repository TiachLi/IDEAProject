package com.atguigu.mpdemo1;

import com.atguigu.mpdemo1.entity.User;
import com.atguigu.mpdemo1.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class Mpdemo1ApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads1() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    @Test
    void contextLoads2() {
        User user =new User();
        user.setAge(21);
        user.setName("sunqi");
        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);
    }
    @Test
    void contextLoads3() {
        User user =new User();
        user.setId(1313101302909263874l);
        user.setAge(21);
        user.setName("zhaoliuliu");
        int i = userMapper.updateById(user);
        System.out.println("i = " + i);
    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker() {
        //根据id查询数据
        User user = userMapper.selectById(1313101302909263874l);
        //进行修改
        user.setAge(201);
        userMapper.updateById(user);
    }

    //多个id批量查询
    @Test
    public void testSelectDemo1() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L));
        System.out.println(users);
    }

   @Test
    public void testSelectByMap(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 21);
        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void testPage() {
        //1 创建page对象
        //传入两个参数：当前页 和 每页显示记录数
        Page<User> page = new Page<>(1,3);
        //调用mp分页查询的方法
        //调用mp分页查询过程中，底层封装
        //把分页所有数据封装到page对象里面
        userMapper.selectPage(page,null);

        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal()); //总记录数
        System.out.println(page.getPages()); //总页数

        System.out.println(page.hasNext()); //下一页
        System.out.println(page.hasPrevious()); //上一页

    }

    //删除操作 物理删除
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1313101302909263874L);
        System.out.println(result);
    }

    //批量删除
    @Test
    public void testDeleteBatchIds() {
        
        int result = userMapper.deleteBatchIds(Arrays.asList(1,2));
        System.out.println(result);
    }

    //mp实现复杂查询操作
    @Test
    public void testSelectQuery() {
        //创建QueryWrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

       // wrapper.ge("id",3).orderByDesc();

        //通过QueryWrapper设置条件
        //ge、gt、le、lt
        //查询age>=30记录
        //第一个参数字段名称，第二个参数设置值
//        wrapper.ge("age",30);

        //eq、ne
        //wrapper.eq("name","lilei");
        //wrapper.ne("name","lilei");

        //between
        //查询年龄 20-30
        // wrapper.between("age",20,30);

        //like
        //wrapper.like("name","岳");

        //orderByDesc
        // wrapper.orderByDesc("id");

        //last
        //wrapper.last("limit 1");

        //指定要查询的列
      //  wrapper.select("id","name");

        wrapper.eq("name","lisi");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

    }




}
