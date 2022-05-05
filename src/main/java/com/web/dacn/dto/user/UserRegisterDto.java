package com.web.dacn.dto.user;


public class UserRegisterDto {
	private String name;
	private String fullname;
	private String password;
	private String email;

	public UserRegisterDto() {
	}

	public UserRegisterDto(String name, String fullname, String password, String email) {
		this.name = name;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
