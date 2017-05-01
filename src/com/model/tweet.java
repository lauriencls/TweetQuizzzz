package com.model;

import java.util.ArrayList;

public class tweet {

	private String id;
	private String author;
	private String content;
	private boolean isAlreadyPlayed;
	private ArrayList<String> falseAuthors;
	
	public tweet(String id, String author, String content, boolean isAlreadyPlayed, ArrayList<String> falseAuthors) {
		super();
		this.id = id;
		this.author = author;
		this.content = content;
		this.isAlreadyPlayed = isAlreadyPlayed;
		this.falseAuthors = falseAuthors;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isAlreadyPlayed() {
		return isAlreadyPlayed;
	}

	public void setPlayed(boolean isAlreadyPlayed) {
		this.isAlreadyPlayed = isAlreadyPlayed;
	}

	public ArrayList<String> getFalseAuthors() {
		return falseAuthors;
	}

	public void setFalseAuthors(ArrayList<String> falseAuthors) {
		this.falseAuthors = falseAuthors;
	}
}
