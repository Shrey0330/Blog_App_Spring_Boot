package com.blog.app.payloads;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.blog.app.entities.Post;
import com.blog.app.entities.User;

public class Commentdto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cid;

	private String title;

	private String content;

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

	public Commentdto() {
		super();
		// TODO Auto-generated constructor stub
	}


	 

}
