package com.example.springboot_security.service;


import com.example.springboot_security.bean.Admin;
import com.example.springboot_security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Resource
    PasswordEncoder passwordEncoder;

    @Bean
    UserDetailsServiceImpl userDerailService(){
        return new UserDetailsServiceImpl();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin userByUserName = userMapper.getUserByUserName(username);
        if (userByUserName==null){
            return null;
        }else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("VIP1"));
            User user =new User(userByUserName.getUserName(),passwordEncoder.encode(userByUserName.getPassWord()),authorities);
            return user;
        }
    }
}
