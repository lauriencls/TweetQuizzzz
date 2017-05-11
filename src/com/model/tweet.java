package com.model;

public class Tweet {

	private String id;
	private String playerName;
	private String author;
	private String content;
	private boolean isAlreadyPlayed;
	private String falseAuthor1;
	private String falseAuthor2;
	private String falseAuthor3;
	
	
	
	public Tweet(String id, String author, String playerName, String content, boolean isAlreadyPlayed, String falseAuthor1,
			String falseAuthor2, String falseAuthor3) {
		super();
		this.id = id;
		this.playerName = playerName;
		this.author = author;
		this.content = content;
		this.isAlreadyPlayed = isAlreadyPlayed;
		this.falseAuthor1 = falseAuthor1;
		this.falseAuthor2 = falseAuthor2;
		this.falseAuthor3 = falseAuthor3;
	}
	
	public Tweet(String author, String content,String falseAuthor1, String falseAuthor2, String falseAuthor3) {
		this.author = author;
		this.content = content;
		this.falseAuthor1 = falseAuthor1;
		this.falseAuthor2 = falseAuthor2;
		this.falseAuthor3 = falseAuthor3;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isAlreadyPlayed() {
		return isAlreadyPlayed;
	}
	public void setAlreadyPlayed(boolean isAlreadyPlayed) {
		this.isAlreadyPlayed = isAlreadyPlayed;
	}
	public String getFalseAuthor1() {
		return falseAuthor1;
	}
	public void setFalseAuthor1(String falseAuthor1) {
		this.falseAuthor1 = falseAuthor1;
	}
	public String getFalseAuthor2() {
		return falseAuthor2;
	}
	public void setFalseAuthor2(String falseAuthor2) {
		this.falseAuthor2 = falseAuthor2;
	}
	public String getFalseAuthor3() {
		return falseAuthor3;
	}
	public void setFalseAuthor3(String falseAuthor3) {
		this.falseAuthor3 = falseAuthor3;
	}


}
