package com.web.dacn.dto;

import java.sql.Date;
import java.util.List;

public class BooksDTO {

	private int id;
	private String name;
	private String thumbnail;
	private List<String> categories;
	private List<String> authors;
	private int view;
	private float price;
	private int status;
	private String description;
	private Date mod_time;

	public BooksDTO() {
	}

	public BooksDTO(int id, String name, String thumbnail, List<String> authors, List<String> categories, int status,
			Date mod_time) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnail = thumbnail;
		this.authors = authors;
		this.categories = categories;
		this.status = status;
		this.mod_time = mod_time;

	}

	public BooksDTO(int id, String name, String thumbnail, int status, Date mod_time) {
		super();
		this.id = id;
		this.name = name;
		this.thumbnail = thumbnail;
		this.status = status;
		this.mod_time = mod_time;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getMod_time() {
		return mod_time;
	}

	public void setMod_time(Date mod_time) {
		this.mod_time = mod_time;
	}

}