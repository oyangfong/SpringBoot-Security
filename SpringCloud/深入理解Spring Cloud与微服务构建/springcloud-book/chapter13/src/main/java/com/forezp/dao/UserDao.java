package com.forezp.dao;


import com.forezp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
*  JpaRepository 默认实现了大多数单表查询的操作
*
* */

public interface UserDao extends JpaRepository<User, Long>{
    // 该方法JPA已经自动实现了，因此不需要进行额外处理
	User findByUsername(String username);
}
