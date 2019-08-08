package com.mysecurity.demo.config.service;

import com.mysecurity.demo.dao.UserMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 此处调用业务类的方法完成用户登录操作
        com.mysecurity.demo.pojo.User ret=userMapper.findByUserName(new com.mysecurity.demo.pojo.User(username));
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        User user = new User(ret.getUserName(), "123456",
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
