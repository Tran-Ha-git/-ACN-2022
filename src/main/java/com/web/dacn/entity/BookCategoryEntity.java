package com.web.dacn.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "bookcategory")
public class BookCategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "parent_id")
	private int parentId;
	@Column(name = "view")
	private int view;
	@Column(name = "slug")
	private String slug;
	@Column(name = "meta_title")
	private String metaTitle;
	@Column(name = "meta_description")
	private String metaDescription;
	@Column(name = "status")
	private int status;

	public BookCategoryEntity() {
	}

	@ManyToMany(mappedBy = "categories")
    private Set<BookEntity> books = new HashSet<>();
	

	public Set<BookEntity> getBooks() {
		return books;
	}



	public void setBooks(Set<BookEntity> books) {
		this.books = books;
	}



	public BookCategoryEntity( String name, int parentId) {
		super();
		
		this.name = name;
		this.parentId = parentId;
		this.view = view;
		this.slug = slug;
		this.metaTitle = metaTitle;
		this.metaDescription = metaDescription;
		this.status = status;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
