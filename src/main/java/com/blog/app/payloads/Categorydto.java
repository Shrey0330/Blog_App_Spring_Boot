package com.blog.app.payloads;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Categorydto {
	private  Integer  catId;
	@NotBlank
	@Size(min=5,message="minimum size is 50")
	private  String  catTitle;
	@NotBlank
	@Size(min=10,max=50,message="maximun size is 50")
	private  String  catDesc;

	public Integer getCatId() {
		return catId;
	}

	public Categorydto() {
		super();
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

	public Categorydto(Integer catId, String catTitle, String catDesc) {
		super();
		this.catId = catId;
		this.catTitle = catTitle;
		this.catDesc = catDesc;
	}
	
}
