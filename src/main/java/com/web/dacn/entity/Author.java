package com.web.dacn.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String fullname;
	
	@Column
	private Date birthday;
	
	@Column
	private String address;
	
	@Column
	private String phone;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String slug;
	
	@Column(name = "meta_title")
	private String metaTitle;
	
	@Column(name = "meta_description")
	private String metaDescription;
	
	@Column
	private Integer status;
	
	@Column(name = "mod_time")
	private Date modTime;
	
	@ManyToOne
	@JoinColumn(name = "mod_user_id", nullable = false)
	private User user;
	
	@ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
	private Set<Book> books = new HashSet<Book>();

	public Author() {}

	public Author(Long id, String fullname, Date birthday, String address, String phone, String description,
			String slug, String metaTitle, String metaDescription, Integer status, Date modTime) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
		this.description = description;
		this.slug = slug;
		this.metaTitle = metaTitle;
		this.metaDescription = metaDescription;
		this.status = status;
		this.modTime = modTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getModTime() {
		return modTime;
	}

	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	
	
}