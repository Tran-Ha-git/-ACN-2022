package com.web.dacn.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column
	private String thumbnail;

	@Column
	private String view;

	@Column
	private String price;
	
	@Column
	private String vip;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String slug;
	
	@Column
	private String meta_title;
	
	@Column
	private String meta_description;
	
	@Column
	private String status;
	
	@Column(nullable = false)
	private Date mod_time;
	
	 @OneToMany(mappedBy="book")
	 private List<Audio> audios = new ArrayList<>();
	 
	 @ManyToOne
	 @JoinColumn(name="mod_user_id", nullable=false)
	 private User user;
	 
	 @ManyToMany(cascade = CascadeType.ALL)
	 @JoinTable(name = "book_author", joinColumns = @JoinColumn(name="book_id"), inverseJoinColumns = @JoinColumn(name="author_id"))
	 private Set<Author> authors = new HashSet<Author>();

	 @ManyToMany(cascade = CascadeType.ALL)
	 @JoinTable(name = "book_bookcategory", joinColumns = @JoinColumn(name="book_id"), inverseJoinColumns = @JoinColumn(name="category_id"))
	 private Set<Bookcategory> categories = new HashSet<Bookcategory>();
	 
	public Book() {}

	public Book(Long id, String name, String thumbnail, String view, String price, String vip, String description,
			String slug, String metaTitle, String meta_description, String status, Date mod_time) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnail = thumbnail;
		this.view = view;
		this.price = price;
		this.vip = vip;
		this.description = description;
		this.slug = slug;
		this.meta_title = metaTitle;
		this.meta_description = meta_description;
		this.status = status;
		this.mod_time = mod_time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
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

	public String getMeta_description() {
		return meta_description;
	}

	public void setMeta_description(String meta_description) {
		this.meta_description = meta_description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getMod_time() {
		return mod_time;
	}

	public void setMod_time(Date mod_time) {
		this.mod_time = mod_time;
	}

	public List<Audio> getAudios() {
		return audios;
	}

	public void setAudios(List<Audio> audios) {
		this.audios = audios;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMeta_title() {
		return meta_title;
	}

	public void setMeta_title(String meta_title) {
		this.meta_title = meta_title;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Set<Bookcategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<Bookcategory> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", thumbnail=" + thumbnail + ", view=" + view + ", price=" + price
				+ ", vip=" + vip + ", description=" + description + ", slug=" + slug + ", metaTitle=" + meta_title
				+ ", meta_description=" + meta_description + ", status=" + status + ", mod_time=" + mod_time + "]";
	}


	
}