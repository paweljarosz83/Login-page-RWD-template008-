package com.paweljarosz.main.controllers;

public class Game {
	
	private Long id;
	
	private String title;
	
	private String thumbnailLink;
	
	private Long version;
	
	private String authorAvatar;
	
	private String author;
	
	private String device;
	
	public Game(Long id,String title,String thumbnailLink,Long version,String authorAvatar,String author,String device){
		this.title = title;
		this.id = id;
		this.thumbnailLink = thumbnailLink;
		this.version = version;
		this.authorAvatar = authorAvatar;
		this.author = author;
		this.device = device;
	}

	
	public Long getVersion() {
		return version;
	}


	public void setVersion(Long version) {
		this.version = version;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnailLink() {
		return thumbnailLink;
	}
	public void setThumbnailLink(String thumbnailLink) {
		this.thumbnailLink = thumbnailLink;
	}


	public String getAuthorAvatar() {
		return authorAvatar;
	}


	public void setAuthorAvatar(String authorAvatar) {
		this.authorAvatar = authorAvatar;
	}


	public String getAuthor() {
		return author;
	}


	public String getDevice() {
		return device;
	}


	public void setDevice(String device) {
		this.device = device;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
	
}
