package com.blog.app.services;

import java.util.List;

import com.blog.app.entities.User;
import com.blog.app.payloads.UserDto;

public interface UserServices {
UserDto createUser(UserDto user);
UserDto updateUser(UserDto user,Integer id);
UserDto getUser(Integer id);
List<UserDto> getAllusers();

User deleteUsers(Integer id);
}
