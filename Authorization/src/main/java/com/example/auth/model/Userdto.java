package com.example.auth.model;

public class Userdto {

	private Long id;
	private String email;
	private String username;
	
	public Userdto() {
		
	}
	@Override
	public String toString() {
		return "Userdto [id=" + id + ", Email=" + email + ", UserName=" + username + ", Roles=" + roles + "]";
	}
	public Long getId() {
		return id;
	}
	public Userdto(Long id, String email, String username, String roles) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.roles = roles;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	private String roles;
}
