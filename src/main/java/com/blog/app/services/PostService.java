package com.blog.app.services;

import java.util.List;

import com.blog.app.entities.Post;
import com.blog.app.payloads.PostResponse;
import com.blog.app.payloads.Postdto;

public interface PostService {
Postdto createPost(Postdto postdto,Integer id,Integer catId ); 
Postdto updatePost(Postdto postdto,Integer pid); 	
void deletePost(Integer pid );
PostResponse getAllPost(int pageSize,int pageNumber,String sortBy,String sortDir);
	Postdto getPostById(Integer pid);
	List<Postdto> getByCategory(Integer catId);
	List<Postdto> getByUser(Integer id,int pageSize,int pageNumber,String sortBy,String sortDir);
	// search post
	List<Postdto> searchPosts(String keyword);
	
}
