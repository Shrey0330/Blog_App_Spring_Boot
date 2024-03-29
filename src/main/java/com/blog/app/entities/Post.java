package com.blog.app.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.blog.app.payloads.Commentdto;

@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	@Column(name = "post_title", length = 100, nullable = false)
	private String title;
	@Column(name = "post_content", length = 1000)
	private String content;
	private String imageName;
	private Date addDate;
	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "user_id")

	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comment = new HashSet<>();

	public Set getComment() {
		return comment;
	}

	public void setComment(Set comment) {
		this.comment = comment;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Post [pid=" + pid + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", addDate=" + addDate + ", category=" + category + ", user=" + user + "]";
	}

}
