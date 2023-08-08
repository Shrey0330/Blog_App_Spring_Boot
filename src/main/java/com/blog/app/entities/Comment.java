package com.blog.app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer cid;

private String title;

private String content;


  @ManyToOne
  private Post post;
 
 

  @ManyToOne private User user;

 
public Integer getCid() {
	return cid;
}

public void setCid(Integer cid) {
	this.cid = cid;
}

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

public Post getPost() { return post; }
 
  public void setPost(Post post) { this.post = post; }
 

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public Comment() {
	super();
	// TODO Auto-generated constructor stub
}

}
