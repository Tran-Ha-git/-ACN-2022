package com.web.dacn.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserDto implements Serializable {

	private Long id;
	private String uname;
	private String password;
	private String fullname;
	private String email;
	private Date birthday;
	private String address;
	private String phone;
	private Integer status;
	private List<String> role;

	public UserDto() {
	}

	public UserDto(Long id, String uname, String password, String fullname, String email, Date birthday, String address,
			String phone, Integer status, List<String> role) {
		super();
		this.id = id;
		this.uname = uname;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
		this.status = status;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

}
