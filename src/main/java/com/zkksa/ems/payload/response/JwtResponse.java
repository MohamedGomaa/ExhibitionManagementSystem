package com.zkksa.ems.payload.response;

import java.util.List;

public class JwtResponse {

	private String token;
	private String type = "Bearer ";
	private Long id;
	private String userName;
	private String email;
	private List<String> roles;

	
	
	public JwtResponse(String accessToken, Long id, String userName, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

}