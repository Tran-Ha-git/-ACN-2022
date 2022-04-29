package com.web.dacn.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDto {
	private Long id;
	private String name;
	private String thumbnail;
	private String description;
	private String slug;
	private String metaTitle;
	private String metaDescription;
	private List<String> authors= new ArrayList<String>();
	private List<String> categories= new ArrayList<String>();
	private String listCategory ="";
	private String listAuthor ="";

	public BookDto() {}

	public BookDto(Long id, String name, String thumbnail, String description, String slug, String metaTitle,
			String metaDescription, String listCategory) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnail = thumbnail;
		this.description = description;
		this.slug = slug;
		this.metaTitle = metaTitle;
		this.metaDescription = metaDescription;
		this.listCategory = listCategory;
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

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getListCategory() {
		return listCategory;
	}

	public void setListCategory(String listCategory) {
		this.listCategory = listCategory;
	}

	public String getListAuthor() {
		return listAuthor;
	}

	public void setListAuthor(String listAuthor) {
		this.listAuthor = listAuthor;
	}

	@Override
	public String toString() {
		return "BookDto [id=" + id + ", name=" + name + ", thumbnail=" + thumbnail + ", description=" + description
				+ ", slug=" + slug + ", metaTitle=" + metaTitle + ", metaDescription=" + metaDescription + "]";
	}
	
	
}
