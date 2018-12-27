package com.accenture.tcf.bars.login.client.bars.login.client.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.tcf.bars.login.client.bars.login.client.BarsLoginServerProxy;
import com.accenture.tcf.bars.login.client.bars.login.client.domain.User;


@Controller
public class ClientController {
	@Autowired
	BarsLoginServerProxy proxy;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@PostMapping("/login")
	public ModelAndView getMe(@RequestParam(value = "username") String username, 
			@RequestParam(value = "password") String password, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notfound");
		User user = proxy.loginUser(username, password);
		if(user.getRole().equals("Admin")||user.getRole().equals("User")) {
			mav.setViewName("client");
			session.setAttribute("username", username);
			session.setAttribute("role", user.getRole());
		}
		mav.addObject("list",proxy.getAllUsers());
		return mav;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		session.invalidate();
		return mav;
	}
	
	@PostMapping("/edit")
	public ModelAndView editUser(@RequestParam(value = "id") int id, @RequestParam(value = "password") String password) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client");
		proxy.updateUser(id, password);
		mav.addObject("list",proxy.getAllUsers());
		return mav;
	}
	
	@GetMapping("/edit/{userid}")
	public ModelAndView editUser(@PathVariable(value = "id") int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("edit");
		mav.addObject("account",proxy.getUserById(id));
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView addUser() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("add");
		return mav;
	}
	
	@PostMapping("/add")
	public ModelAndView addUser(HttpServletRequest request) {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setRole(request.getParameter("role"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client");
		proxy.createUser(user);
		mav.addObject("list",proxy.getAllUsers());
		return mav;
	}
	
	@PostMapping("/delete")
	public ModelAndView deleteUser(@RequestParam(value = "id") int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client");
		proxy.deleteUser(id);
		mav.addObject("list",proxy.getAllUsers());
		return mav;
	}

}
