package com.accenture.tcf.bars.login.bars.login.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.tcf.bars.login.bars.login.server.domain.User;
import com.accenture.tcf.bars.login.bars.login.server.exception.UserNotFoundException;
import com.accenture.tcf.bars.login.bars.login.server.service.UserService;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) throws UserNotFoundException {
		User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for id: " + id));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/login")	
	public User login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
		User user =  userService.findByUsername(username);
		if(user.getPassword().equals(password))
			return user;
		throw new UserNotFoundException("Wrong password");
	}
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userService.createUser(user);		
	}
	
	@PostMapping("/edit")
	public ResponseEntity<User> updateUser(@RequestParam(value = "id") int id, @RequestParam(value = "password") String password) 
			throws UserNotFoundException {
		User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for id: " + id));
		user.setPassword(password);
		userService.updateUser(user);
		final User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	@DeleteMapping("/delete")
	public Map<String, Boolean> deleteUser(@RequestParam(value = "id") int id) throws UserNotFoundException {
		User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for id: " + id));
		userService.deleteUser(user);
		Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
	}
	
	
	
}
