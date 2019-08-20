package com.forezp.controller;


import com.forezp.domain.User;
import com.forezp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(value = "/registry",method = RequestMethod.POST)
	public User createUser( @RequestParam("username") String username
	, @RequestParam("password") String password) {
		return userService.create(username,encoder.encode(password));
	}

}
