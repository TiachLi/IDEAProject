/*
package daoTest;

import com.dao.HouseDao;
import com.dao.UserDao;
import com.domain.House;
import com.domain.HousePageBean;
import com.domain.ResultInfo;
import com.domain.User;
import com.service.HouseService;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class HouseTest {
    @Autowired
    HouseDao houseDao;
    @Autowired
    HouseService houseService;
*/
/*   @Test
    public void test1(){
       List<House> allHouse = houseService.findAllHouse();
       System.out.println(allHouse);

   }*//*

*/
/*   @Test
    public void test2(){
        ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = ac.getBean("userDao", UserDao.class);
        System.out.println(userDao.findTotalCounts());
    }

*//*

*/
/*    @Test
    public void test4(){
        House house =new House();
        house.setHouseId(2);
        house.setHousePrice("6200");
        houseDao.updateHouse(house);
    }*//*

*/
/*    @Test
    public  void addHouseTest(){
        ResultInfo info =new ResultInfo();
        House house =new House();
        house.setHouseName("test");
         info = houseService.addHouse(house);
        System.out.println(info.getErrorMsg());
    }*//*

*/
/*    @Test
    public void findAllTest(){
        System.out.println(houseService.findAllHouse());
    }*//*


    @Test
    public void findTotalTest(){
        House house =new House();
        house.setSubTel("12");
        house.setHouseAddress("河南");
        List<House> pageWithCondition = houseDao.findByPageWithCondition(house,1,5);
        int totalCountsWithCondition = houseDao.findTotalCountsWithCondition(house);
        System.out.println(pageWithCondition);
        System.out.println(totalCountsWithCondition);
    }

    @Test
    public void pageQueryWithConditionTest(){
*/
/*        HousePageBean housePageBean = houseService.pageQueryWithCondition(2, 5, new House());
        System.out.println(housePageBean);*//*

    }

*/
/*    @Test
    public void findByNameTest(){
        House test = houseDao.findByName("test");
        System.out.println(test);
    }*//*


    @Test
    public void updateTest(){
*/
/*      House house =new House();
        house.setHouseId(16);
        house.setHouseName("test");
        house.setHouseAddress("西南");
        ResultInfo info = houseService.updateHouse(house);
        System.out.println(info);*//*

    }



}
*/
