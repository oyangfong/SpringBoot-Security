package com.mysecurity.demo.dao;


import com.mysecurity.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
  List<User> findAll();
  int add(User user);
  
  User checkUser(User user);
  
  User findById(int id);
}
