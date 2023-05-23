package com.blog.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.app.repositories.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {
	@Autowired
	private UserRepo ur;

	@Test
	void contextLoads() {
	}

	
	
	@Test
	public void reT() {
		System.out.println(ur.getClass().getName()+" "+ ur.getClass().getPackageName());
	}
	
}
