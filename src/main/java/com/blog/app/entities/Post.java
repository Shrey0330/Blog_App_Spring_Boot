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
@Table(name="post")
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer pid;
	@Column(name="post_title",length=100,nullable=false)
private String ptitle;
	@Column(name="post_content",length=500)
private String content;
private String imageName;
private Date addDate;
@ManyToOne
private Category category;
@ManyToOne
private User user;

}
