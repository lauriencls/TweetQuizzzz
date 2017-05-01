package com.entities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class TweetEntity {
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	Long id;
	
	@Persistent
	String author;
	
	@Persistent
	String content;
	
	/* Faux auteurs du tweet présents dans les propositions de jeu */
	
	@Persistent
	String falseAuthor1;
	
	@Persistent
	String falseAuthor2;
	@Persistent
	String falseAuthor3;
	
	/* Méthodes */
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
