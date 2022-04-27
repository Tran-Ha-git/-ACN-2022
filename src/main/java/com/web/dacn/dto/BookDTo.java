package com.web.dacn.dto;

import java.util.List;

import com.web.dacn.entity.AuthorEntity;

public class BookDTo {
	private int id;
	private String name;
	private String thumbnail;
	private float price;
	private boolean vip;
	private String description;

	private String slug;
	private String metaTitle;
	private String metaDescription;
	private int status;
	private String fullName;
	private String nameCategory;
	private List<String> pdf;
	private List<String> author;
	private List<String> category;

	public BookDTo() {

	}

	public BookDTo(String name, String thumbnail, float price, boolean vip, String description, String slug,
			String metaTitle, String metaDescription, int status, String fullName, String nameCategory) {
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
		this.fullName = fullName;
		this.nameCategory = nameCategory;
	}

	public BookDTo(String name, String thumbnail, float price, boolean vip, String description, String slug,
			String metaTitle, String metaDescription, int status) {
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

	}

	public List<String> getAuthor() {
		return author;
	}

	public void setAuthor(List<String> author) {
		this.author = author;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public List<String> getPdf() {
		return pdf;
	}

	public void setPdf(List<String> pdf) {
		this.pdf = pdf;
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

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isVip() {
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", name=" + name + ", thumbnail=" + thumbnail + ", price=" + price + ", vip=" + vip
				+ ", description=" + description + ", slug=" + slug + ", metaTitle=" + metaTitle + ", metaDescription="
				+ metaDescription + ", status=" + status + ", fullName=" + fullName + ", nameCategory=" + nameCategory
				+ "]";
	}

}
