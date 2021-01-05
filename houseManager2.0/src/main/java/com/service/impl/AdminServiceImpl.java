package com.service.impl;

import com.dao.AdminDao;
import com.domain.Admin;
import com.domain.ResultInfo;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    AdminDao adminDao;
    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public ResultInfo register(Admin admin) {
        ResultInfo registerResult=new ResultInfo();
        //根据用户名查询数据库，判断用户名是否重复
        Admin oneByName = adminDao.findOneByName(admin.getAdminName());
        if (oneByName!=null){
            registerResult.setFlag(false);
            registerResult.setErrorMsg("用户名重复");
            return registerResult;
        }
        //利用手机号查询数据库，判断手机号是否重复
        Admin oneByTel = adminDao.findOneByTel(admin.getAdminTel());
        if (oneByTel!=null){
            registerResult.setFlag(false);
            registerResult.setErrorMsg("手机号重复");
            return registerResult;
        }
        //验证通过，执行增加操作
        adminDao.addAdmin(admin);
        registerResult.setFlag(true);
        registerResult.setErrorMsg("登陆成功");
        return registerResult;
    }

    @Override
    public ResultInfo login(Admin admin) {
        ResultInfo info =new ResultInfo();
        //调用dao层根据用户名查询管理员信息
        Admin oneByName = adminDao.findOneByName(admin.getAdminName());
        //如果查询不到，则判断用户名错误
        if (oneByName==null){
            info.setFlag(false);
            info.setErrorMsg("用户名错误");
            return info;
        }
        //如果密码不对则返回密码错误
        if (oneByName.getAdminPassword().equals(admin.getAdminPassword())){
            info.setFlag(true);
            return info;
        }
        info.setFlag(false);
        info.setErrorMsg("密码错误");
        //返回结果集
        return info;
    }
}
