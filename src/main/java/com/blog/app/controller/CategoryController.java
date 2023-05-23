package com.blog.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.entities.Category;
import com.blog.app.entities.User;
import com.blog.app.payloads.Categorydto;
import com.blog.app.payloads.Response;
import com.blog.app.payloads.UserDto;
import com.blog.app.services.CategoryService;
import com.blog.app.services.UserServices;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService createService;
	//post
	@PostMapping("/")
	public ResponseEntity<Categorydto > createCategory(@Valid @RequestBody Categorydto catgorydto){
		Categorydto createCategorydto=this.createService.createCategory(catgorydto);
	return new ResponseEntity(createCategorydto,HttpStatus.CREATED);
	}
	
	//get
	@GetMapping("/{cid}")
	public ResponseEntity<Categorydto> getCategory(@PathVariable Integer cid)
	{
		return  ResponseEntity.ok(this.createService.getCategory(cid));
	}
	@PutMapping("/{cid}")
	public ResponseEntity<Categorydto > updateUser(@Valid @RequestBody Categorydto catdto,@PathVariable Integer cid){
		Categorydto updatedCategorydto=this.createService.updateCategory(catdto, cid);
	return  ResponseEntity.ok(updatedCategorydto);
	}
//delete
@DeleteMapping("/{cid}")
	public ResponseEntity<Response<Categorydto> > deleteUser(@PathVariable Integer cid){
	Categorydto categorydto=this.createService.deleteCategory(cid);
    
    Response<Categorydto> res=new Response();
    res.setData(categorydto);
    res.setMsg("deleted category");
	return  ResponseEntity.ok(res);
	}
@GetMapping("/")
public ResponseEntity<List<Categorydto>> getCategories()
{
	return ResponseEntity.ok(this.createService.getAllCategory());
}

}
