package com.blog.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Category;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.Categorydto;
import com.blog.app.payloads.Response;
import com.blog.app.repositories.CategoryRepo;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelmapper;
	@Override
	public Categorydto createCategory(Categorydto catdto) {
		// TODO Auto-generated method stub
		Category cat=this.modelmapper.map(catdto, Category.class);
		Category addcat=this.categoryRepo.save(cat);
		
		return this.modelmapper.map(addcat, Categorydto.class);
	}

	@Override
	public Categorydto updateCategory(Categorydto catdto, Integer cid) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(cid).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", cid));
		cat.setCatTitle(catdto.getCatTitle());
		cat.setCatDesc(catdto.getCatDesc());
		Category updatedCat=this.categoryRepo.save(cat);
		return this.modelmapper.map(updatedCat, Categorydto.class);
	}

	@Override
	public  Categorydto deleteCategory(Integer cid) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(cid).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", cid));
 this.categoryRepo.deleteById(cid);
		return this.modelmapper.map(cat, Categorydto.class);

	}

	@Override
	public Categorydto getCategory(Integer cid) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(cid).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", cid));
		return this.modelmapper.map(cat, Categorydto.class);
	}

	@Override
	public List<Categorydto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> catlist=this.categoryRepo.findAll();;
		List<Categorydto> catdto=catlist.stream().map((cat)->this.modelmapper.map(cat, Categorydto.class)).collect(Collectors.toList());
		return catdto;
	}

}
