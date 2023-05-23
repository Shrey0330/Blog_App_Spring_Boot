package com.blog.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.app.entities.User;
import com.blog.app.payloads.UserDto;
import com.blog.app.repositories.UserRepo;
import com.blog.app.exceptions.*;

@Service
public class UserServicesimpl implements UserServices {
	@Autowired
	private UserRepo userrepo;
	@Autowired
private ModelMapper modelmapper;
	@Override
	public UserDto createUser(UserDto userdto) {
		// TODO Auto-generated method stub
	User user=this.dtoToUser(userdto);
	User savedUser=this.userrepo.save(user);
		return this.UserTodto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer id) {
		// TODO Auto-generated method stub
		User user=this.userrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
		
		
		user.setAbout(userdto.getAbout());
		user.setEmail(userdto.getEmail());
		user.setName(userdto.getName());
		user.setPassword(userdto.getName());
		User upateduser= this.userrepo.save(user);
		return this.UserTodto(upateduser);
	}

	@Override
	public UserDto getUser(Integer id) {
		// TODO Auto-generated method stub
		User user=this.userrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
		return this.UserTodto(user);
	}

	@Override
	public List<UserDto> getAllusers() {
		// TODO Auto-generated method stub
		List<User> users=this.userrepo.findAll();
		List<UserDto> userdtos=	users.stream().map(user->this.UserTodto(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public User deleteUsers(Integer id) {
		// TODO Auto-generated method stub
		User user=this.userrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
		System.out.println(user);
		this.userrepo.delete(user);
		return user;
	}
	private User dtoToUser(UserDto userdto) {
		/*
		 * User user=new User(); user.setId(userdto.getId());
		 * user.setAbout(userdto.getAbout()); user.setEmail(userdto.getEmail());
		 * user.setName(userdto.getName()); user.setPassword(userdto.getPassword());
		 this is for manually conversion.Now for modelmapper.*/
	User user=this.modelmapper.map(userdto, User.class);
		return user;	
	}
	public UserDto UserTodto(User user) {
		/*
		 * UserDto userdto=new UserDto(); userdto.setAbout(user.getAbout());
		 * userdto.setEmail(user.getEmail()); userdto.setId(userdto.getId());
		 * userdto.setName(user.getName()); userdto.setPassword(user.getPassword());
		  this is for manually conversion.Now for modelmapper. */
	UserDto userdto=this.modelmapper.map(user, UserDto.class);
		return userdto;
	}

}
