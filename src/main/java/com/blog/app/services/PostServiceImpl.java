package com.blog.app.services;

import org.springframework.data.domain.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.PostResponse;
import com.blog.app.payloads.Postdto;
import com.blog.app.repositories.CategoryRepo;
import com.blog.app.repositories.PostRepo;
import com.blog.app.repositories.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Postdto createPost(Postdto postdto, Integer id, Integer catId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User id", id));
		Category category = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catId", catId));
		Post post = this.modelMapper.map(postdto, Post.class);
		post.setAddDate(new Date());
		post.setImageName("default.png");
		post.setCategory(category);
		post.setUser(user);
		System.out.println(id + " " + catId);
		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, Postdto.class);
	}

	// update
	@Override
	public Postdto updatePost(Postdto postdto, Integer pid) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", pid));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName(postdto.getImageName());
		Post updatedPost = this.postRepo.save(post);

		return this.modelMapper.map(updatedPost, Postdto.class);
	}

//delete
	@Override
	public void deletePost(Integer pid) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", pid));

		this.postRepo.delete(post);

	}

	@Override
	public PostResponse getAllPost(int pageSize, int pageNumber, String sortBy, String sortDir) {
		/*
		 * int pageSize=5; int pageNumber=1;
		 */
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
		// TODO Auto-generated method stub
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> posts = pagePost.getContent();

		List<Postdto> postdtos = posts.stream().map((post) -> this.modelMapper.map(post, Postdto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postdtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPages(pagePost.isLast());
		return postResponse;
	}

//get single by id
	@Override
	public Postdto getPostById(Integer pid) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", pid));

		return this.modelMapper.map(post, Postdto.class);
	}

	@Override
	public List<Postdto> getByCategory(Integer catId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", catId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<Postdto> postdtos = posts.stream().map((post) -> this.modelMapper.map(post, Postdto.class))
				.collect(Collectors.toList());
		return postdtos;

	}

	// getbyuser
	@Override
	
	public List<Postdto> getByUser(Integer id, int pageSize, int pageNumber, String sortBy,String sortDir) {
		
		// TODO Auto-generated method stub
		/*
		 * User user=this.userRepo.findById(id).orElseThrow(()->new
		 * ResourceNotFoundException("User", "User id", id)); List<Post>
		 * posts=this.postRepo.findByUser(user); List<Postdto> postdtos=
		 * posts.stream().map((post)->this.modelMapper.map(post,
		 * Postdto.class)).collect(Collectors.toList()); return postdtos;
		 */
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "User id", id));
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
		// TODO Auto-generated method stub
		List<Post> posts=this.postRepo.findByUser(user); 
		Page<Post> pagePost =  this.postRepo.findAll(p);
		List<Post> postss = pagePost.getContent();

		org.springframework.data.domain.Pageable page=PageRequest.of(pageNumber, pageSize, sort);
		List<Post> pts=this.postRepo.findAllByUserId(id, page);
		List<Postdto> postdtos = pts.stream().map((post) -> this.modelMapper.map(post, Postdto.class))
				.collect(Collectors.toList());
		/*
		 * PostResponse postResponse = new PostResponse();
		 * postResponse.setContent(postdtos);
		 * postResponse.setPageNumber(pagePost.getNumber());
		 * postResponse.setPageSize(pagePost.getSize());
		 * postResponse.setTotalElements(pagePost.getTotalElements());
		 * postResponse.setTotalPages(pagePost.getTotalPages());
		 * postResponse.setLastPages(pagePost.isLast());
		 */
		return postdtos;
		

	}

	@Override
	public List<Postdto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<Postdto> postdtos = posts.stream().map((post) -> this.modelMapper.map(post, Postdto.class))
				.collect(Collectors.toList());
		return postdtos;
	}

}
