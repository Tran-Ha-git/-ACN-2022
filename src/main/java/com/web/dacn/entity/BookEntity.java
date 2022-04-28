package com.web.dacn.entity;

import java.sql.Date;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "authors,categories")

@Entity
@Table(name = "book")
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "view")
	private int view;

	@Column(name = "price")
	private float price;

	@Column(name = "vip")
	private boolean vip;

	@Column(name = "description")
	private String description;

	@Column(name = "slug")
	private String slug;

	@Column(name = "meta_title")
	private String metaTitle;
	@Column(name = "meta_description")
	private String metaDescription;

	@Column(name = "status")
	private int status;

	@Column(name = "mod_time")
	private Date mod_time;

	@Column(name = "mod_user_id")
	private int mod_user_id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false, updatable = false) })

	private Set<AuthorEntity> authors;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_bookcategory", joinColumns = {
			@JoinColumn(name = "`book_id`", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, updatable = false) })

	private Set<BookCategoryEntity> categories;

	public BookEntity() {
	}

	public BookEntity(String name, String thumbnail, float price, boolean vip, String description, String slug,
			String metaTitle, String metaDescription, int status, Date mod_time, int mod_user_id, AuthorEntity authors,
			BookCategoryEntity categories) {
		super();

		this.name = name;
		this.thumbnail = thumbnail;

		this.price = price;
		this.vip = vip;
		this.description = description;
		this.slug = slug;
		this.metaTitle = metaTitle;
		this.metaDescription = metaDescription;
		this.status = status;
		this.mod_time = mod_time;
		this.mod_user_id = mod_user_id;
		this.authors = Stream.of(authors).collect(Collectors.toSet());
		this.authors.forEach(x -> x.getBooks().add(this));
		this.categories = Stream.of(categories).collect(Collectors.toSet());
		this.categories.forEach(x -> x.getBooks().add(this));
	}

	public Set<BookCategoryEntity> getCategories() {
		return categories;
	}

	public void setCategories(Set<BookCategoryEntity> categories) {
		this.categories = categories;
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

	public boolean isVip() {
		return vip;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<AuthorEntity> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<AuthorEntity> authors) {
		this.authors = authors;
	}

	public int getId() {
		return id;
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

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean Vip() {
		return vip;
	}

	public void setVip(boolean vip) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getMod_time() {
		return mod_time;
	}

	public void setMod_time(Date mod_time) {
		this.mod_time = mod_time;
	}

	public int getMod_user_id() {
		return mod_user_id;
	}

	public void setMod_user_id(int mod_user_id) {
		this.mod_user_id = mod_user_id;
	}

}
