package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public void save(SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }


    public List<SysUser> findAll() {
        return userDao.findAll();
    }


    public Map<String, Object> toAddRolePage(Integer id) {
        List<SysRole> allRoles = roleService.findAll();
        List<Integer> myRoles = userDao.findRolesByUid(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allRoles", allRoles);
        map.put("myRoles", myRoles);
        return map;
    }


    public void addRoleToUser(Integer userId, Integer[] ids) {
        userDao.removeRoles(userId);
        for (Integer rid : ids) {
            userDao.addRoles(userId, rid);
        }
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser byName = userDao.findByName(username);
        if (byName==null){
            return null;
        }else {
            List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
            List<SysRole> roles = byName.getRoles();
            for (SysRole role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            User user = new User(byName.getUsername(), passwordEncoder.encode(byName.getPassword()), authorities);
            return user;
        }
    }
}
