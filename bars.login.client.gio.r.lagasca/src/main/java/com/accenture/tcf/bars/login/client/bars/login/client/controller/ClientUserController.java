package com.accenture.tcf.bars.login.client.bars.login.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.tcf.bars.login.client.bars.login.client.BarsLoginServerProxy;
import com.accenture.tcf.bars.login.client.bars.login.client.domain.User;

@RestController
public class ClientUserController {
	
	@Autowired
	BarsLoginServerProxy proxy;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return proxy.getAllUsers();
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return proxy.createUser(user);
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value = "userid")  int userid) {
		return proxy.getUserById(userid);		
	}
	
	@PostMapping("/users/edit")
	public User updateUser(@RequestParam(value = "userid") int userid,@RequestParam(value = "password") String password) {
		return proxy.updateUser(userid, password);
	}
	
	@PostMapping("/users/delete")
	public User deleteUser(@RequestParam(value = "userid")  int userid) {
		return proxy.deleteUser(userid);
	}
	

}
	