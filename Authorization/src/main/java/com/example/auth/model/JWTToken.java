package com.example.auth.model;

public class JWTToken {
	
	private String token;
	private String type;
	private String validuntil;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValiduntil() {
		return validuntil;
	}
	public void setValiduntil(String validuntil) {
		this.validuntil = validuntil;
	}

}
