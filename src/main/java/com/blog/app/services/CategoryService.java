package com.blog.app.services;

import java.util.List;

import com.blog.app.payloads.Categorydto;

public interface CategoryService {
//create	
public Categorydto createCategory(Categorydto catdto); 
//update
Categorydto updateCategory(Categorydto catdto,Integer cid);
//delete
Categorydto deleteCategory(Integer cid);
//get
Categorydto getCategory(Integer cid);
//getall
List<Categorydto> getAllCategory();
}

