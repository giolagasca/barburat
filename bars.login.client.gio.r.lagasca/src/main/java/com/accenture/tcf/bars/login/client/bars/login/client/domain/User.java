package com.accenture.tcf.bars.login.client.bars.login.client.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	
	public Long id;
	private String username;
    private String password;
    private String  role;
    
	
}
