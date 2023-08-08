package com.blog.app.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Integer  catId;
@Column(name="title")
private  String  catTitle;
@Column(name="description")

private  String  catDesc;
public Integer getCatId() {
	return catId;
}
public void setCatId(Integer catId) {
	this.catId = catId;
}
public String getCatTitle() {
	return catTitle;
}
public void setCatTitle(String catTitle) {
	this.catTitle = catTitle;
}
public String getCatDesc() {
	return catDesc;
}
public void setCatDesc(String catDesc) {
	this.catDesc = catDesc;
}
public Category() {
	super();
	// TODO Auto-generated constructor stub
}
public Category(Integer catId, String catTitle, String catDesc) {
	super();
	this.catId = catId;
	this.catTitle = catTitle;
	this.catDesc = catDesc;
}
@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
private Set<Post> posts=new HashSet<>();
}
