package com.web.dacn.dto;

public class AudioDto {
	private Long id;
	private String name;
	private String url ;
	private Integer priority ;
	public AudioDto() {}
	public AudioDto(Long id, String name, String url, Integer priority) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.priority = priority;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
