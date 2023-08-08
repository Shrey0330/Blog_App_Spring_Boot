package com.blog.app.controller;

import java.util.List;
import java.util.Map;

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

import com.blog.app.entities.User;
import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.Response;
import com.blog.app.payloads.UserDto;
import com.blog.app.services.UserServices;

@RestController
@RequestMapping("/api/users")
public class Usercontroller {

@Autowired
	private UserServices userservice;
	//post
	@PostMapping("/")
	public ResponseEntity<UserDto > createUser(@Valid @RequestBody UserDto userdto){
		UserDto createUserDto=this.userservice.createUser(userdto);
	return new ResponseEntity(createUserDto,HttpStatus.CREATED);
	}
	
	//get
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer id)
	{
		return  ResponseEntity.ok(this.userservice.getUser(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<UserDto > updateUser(@Valid @RequestBody UserDto userdto,@PathVariable Integer id){
		UserDto updatedUserDto=this.userservice.updateUser(userdto, id);
	return  ResponseEntity.ok(updatedUserDto);
	}
//delete
@DeleteMapping("/{id}")
	public ResponseEntity<Response<User> > deleteUser(@PathVariable Integer id){
    User user =this.userservice.deleteUsers(id);
    
    Response<User> res=new Response();
    res.setData(user);
    res.setMsg("deleted");
	return  ResponseEntity.ok(res);
	}
//getall
@GetMapping("/")
public ResponseEntity<List<UserDto>> getUsers()
{
	return ResponseEntity.ok(this.userservice.getAllusers());
}


}
