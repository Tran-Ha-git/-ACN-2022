package com.web.dacn.entity;

import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bookcategory")
public class Bookcategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column( nullable = false, length = 50)
	private String name;

	@Column( nullable = false)
	private Long view;
	
	@Column( nullable = false)
	private String slug;
	
	@Column
	private String meta_title;
	
	@Column( nullable = false)
	private String meta_description ;
	
	@Column
	private Integer status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	private Bookcategory parentCategory;
	
	@OneToMany(mappedBy = "parentCategory")
	private Set <Bookcategory> childCategory = new HashSet<Bookcategory>();
	
	@ManyToMany(mappedBy = "categories")
	private Set<Book> books = new HashSet<Book>();

	public Bookcategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bookcategory(Long id, String name, Long view, String slug, String meta_title, String meta_description,
			Integer status) {
		super();
		this.id = id;
		this.name = name;
		this.view = view;
		this.slug = slug;
		this.meta_title = meta_title;
		this.meta_description = meta_description;
		this.status = status;
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

	public Long getView() {
		return view;
	}

	public void setView(Long view) {
		this.view = view;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getMeta_title() {
		return meta_title;
	}

	public void setMeta_title(String meta_title) {
		this.meta_title = meta_title;
	}

	public String getMeta_description() {
		return meta_description;
	}

	public void setMeta_description(String meta_description) {
		this.meta_description = meta_description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Bookcategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Bookcategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<Bookcategory> getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(Set<Bookcategory> childCategory) {
		this.childCategory = childCategory;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}


	
	
}