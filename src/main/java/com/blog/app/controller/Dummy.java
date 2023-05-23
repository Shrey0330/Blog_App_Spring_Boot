package com.blog.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Dummy {
@RequestMapping("/")
public String home() {
	return "home";
}
}
