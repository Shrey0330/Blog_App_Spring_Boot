package com.blog.app.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.blog.app.entities.Category;
import com.blog.app.entities.Comment;
import com.blog.app.entities.User;

public class Postdto {
	private Integer pid;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	private String title;
	
private String content;
private String imageName;
private Date addDate;

private Categorydto category;

/*
 * private UserDto user; private Set<Commentdto> comment=new HashSet<>();
 */
/*
 * public Set<Commentdto> getComment() { return comment; } public void
 * setComment(Set<Commentdto> comment) { this.comment = comment; }
 */
public Categorydto getCategory() {
	return category;
}
public void setCategory(Categorydto category) {
	this.category = category;
}
/*
 * public UserDto getUser() { return user; } public void setUser(UserDto user) {
 * this.user = user; }
 */



public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getImageName() {
	return imageName;
}
public void setImageName(String imageName) {
	this.imageName = imageName;
}
public Date getAddDate() {
	return addDate;
}
public void setAddDate(Date addDate) {
	this.addDate = addDate;
}
public Postdto() {
	super();
	// TODO Auto-generated constructor stub
}

}
