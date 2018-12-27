package com.accenture.tcf.bars.login.client.bars.login.client;


import java.util.List;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.tcf.bars.login.client.bars.login.client.domain.User;



@FeignClient(name="bars-login-netflix-zuul")
@RibbonClient(name="bars-login-server-gio-r-lagasca")
public interface BarsLoginServerProxy {
	
	@GetMapping("/bars-login-server-gio-r-lagasca/users")
	public List<User> getAllUsers();
	
	@GetMapping("/bars-login-server-gio-r-lagasca/users/{id}")
	User getUserById(@PathVariable(value = "userid")  int id);
	
	@PostMapping("/bars-login-server-gio-r-lagasca/users")
	User createUser(@RequestBody User user);
	
	@PostMapping("/bars-login-server-gio-r-lagasca/edit")
	User updateUser(@RequestParam(value = "id") int id,@RequestParam(value = "password") String password);

	@DeleteMapping("/bars-login-server-gio-r-lagasca/delete")
	User deleteUser(@RequestParam(value = "id")  int id);
	
	@PostMapping("/bars-login-server-gio-r-lagasca/login")
	User loginUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password);
}
