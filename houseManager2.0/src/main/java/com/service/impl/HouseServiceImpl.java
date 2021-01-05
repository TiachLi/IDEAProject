package com.service.impl;

import com.dao.HouseDao;
import com.dao.UserDao;
import com.domain.*;
import com.service.HouseService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("houseService")
public class HouseServiceImpl implements HouseService {

    private HouseDao houseDao;
    @Autowired
    public void setHouseDao(HouseDao houseDao) {
        this.houseDao = houseDao;
    }

    @Autowired
    private UserDao userDao;

    @Override
    public ResultInfo addHouse(House house) {
        ResultInfo info =new ResultInfo();
        //根据房源名查询数据库
        House byName = houseDao.findByName(house.getHouseName());
        //如果已存在，给出异常和错误信息
        if (byName!=null){
            info.setFlag(false);
            info.setErrorMsg("房源名已存在");
            return info;
        }
        //如果预约人手机号不为空且存在
        if (house.getSubTel().length()>0&&house.getSubTel()!=null){
            //根据预约人手机号查询客户信息
            User userByTel = userDao.findByTel(house.getSubTel());
            //客户不存在，给出异常和错误信息
            if (userByTel==null){
                info.setFlag(false);
                info.setErrorMsg("预约人信息不存在,是否<a href='addUser.html'>立即添加</a>？");
                return info;
            }
        }
        //执行添加操作
        houseDao.addHouse(house);
        info.setFlag(true);
        return info;
    }

    @Override
    public List<House> findAllHouse() {
         return houseDao.findAllHouses();
     }

     @Override
     public int findTotalCounts() {
         House house =new House();
         return houseDao.findTotalCountsWithCondition(house);
     }

    @Override
    public HousePageBean pageQueryWithCondition(int currentPage, int pageSize, House house) {
        //定义每页要展示的数据对象
        HousePageBean housePageBean =new HousePageBean();
        //获得每页要显示的客户信息
        List<House> houses=houseDao.findByPageWithCondition(house,(currentPage-1)*pageSize+1,(currentPage-1)*pageSize+pageSize);
        //获得总记录数
        int totalCounts=houseDao.findTotalCountsWithCondition(house);
        //获得总页数
        int totalPages=totalCounts%pageSize==0?totalCounts/pageSize:totalCounts/pageSize+1;
        //设置每页要显示的数据
        housePageBean.setCurrentPage(currentPage);
        housePageBean.setHouses(houses);
        housePageBean.setPageSize(pageSize);
        housePageBean.setTotalCount(totalCounts);
        housePageBean.setTotalPage(totalPages);
        //返回数据
        return housePageBean;
    }


    @Override
    public  void  deleteHouse(int houseId){
        houseDao.deleteHouseById(houseId);
    }


    @Override
    public House findOneById(int id) {
        return houseDao.findOneById(id);
    }

    @Override
    public ResultInfo updateHouse(House house) {
        ResultInfo info =new ResultInfo();
        //根据房源名查询数据库
        House byName = houseDao.findByName(house.getHouseName());
        //如果房源信息已存在，且和传入的房源不是同一个房源
        if (byName!=null&&house.getHouseId()!=byName.getHouseId()){
            //判断小区名是否重复
            if (house.getHouseName().equals(byName.getHouseName())){
                info.setFlag(false);
                info.setErrorMsg("房源名重复！");
                return info;
            }
        }
        //判断预约人信息是否存在
        if (house.getSubTel().length()>0&&house.getSubTel()!=null){
            User userByTel = userDao.findByTel(house.getSubTel());
            if (userByTel==null){
                info.setFlag(false);
                info.setErrorMsg("预约人信息不存在,是否<a href='addUser.html'>立即添加</a>？");
                return info;
            }
        }
        info.setFlag(true);
        houseDao.updateHouse(house);
        return info;
    }

}
