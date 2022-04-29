package com.web.dacn.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	public static final String ADMIN = "ADMIN";
	public static final String CLIENT = "CLIENT";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uname", nullable = false, unique = true)
	private String uname;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String fullname;

	@Column(nullable = false)
	private String email;

	private Date birthday;

	private String address;

	private String phone;

	private Integer status;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
	private List<Role> roles = new ArrayList<>();
	
	@OneToMany(mappedBy="user", targetEntity = Book.class)
    private List<Book> books = new ArrayList<>();

	@OneToMany(mappedBy = "user", targetEntity = Author.class)
	private List<Author> authors = new ArrayList<>();
	
	public User() {
	}

	public User(Long id, String uname, String password, String fullname, String email, Date birthday, String address,
			String phone, Integer status) {
		this.id = id;
		this.uname = uname;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
		this.status = status;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", password=" + password + ", fullname=" + fullname + ", email="
				+ email + ", birthday=" + birthday + ", address=" + address + ", phone=" + phone + ", status=" + status
				+ "]";
	}

}
