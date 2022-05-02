package com.web.dacn.entity;

import java.sql.Date;
import java.util.List;
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

import com.web.dacn.entity.quote.Quote;
import com.web.dacn.entity.user.Role;
import com.web.dacn.entity.user.User;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@EqualsAndHashCode(exclude = "authors,categories")
@NoArgsConstructor
@AllArgsConstructor
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
	private Date modTime;

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
		this.modTime = mod_time;
		this.mod_user_id = mod_user_id;
		this.authors = Stream.of(authors).collect(Collectors.toSet());
		this.authors.forEach(x -> x.getBooks().add(this));
		this.categories = Stream.of(categories).collect(Collectors.toSet());
		this.categories.forEach(x -> x.getBooks().add(this));
	}

	

}
