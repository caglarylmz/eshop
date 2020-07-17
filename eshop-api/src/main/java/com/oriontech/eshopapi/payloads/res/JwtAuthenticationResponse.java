package com.oriontech.eshopapi.payloads.res;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String token;
    private String type;
    private String name;
	private String username;
	private String email;
	private List<String> roles;
	
	public JwtAuthenticationResponse(String token, String name, String username, String email, List<String> roles) {
		super();
		this.token = token;
		this.type= "Bearer"; 
		this.name = name;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
	
	
}