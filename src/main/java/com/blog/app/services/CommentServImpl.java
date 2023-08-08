package com.blog.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Comment;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.Commentdto;
import com.blog.app.payloads.Postdto;
import com.blog.app.repositories.CommentsRepo;
import com.blog.app.repositories.PostRepo;
import com.blog.app.repositories.UserRepo;

@Service
public class CommentServImpl implements CommentService {
@Autowired
	private CommentsRepo commentRepo;
@Autowired
private PostRepo postRepo;
@Autowired
private ModelMapper modelMapper;
@Autowired
private UserRepo userrepo;
	@Override
	public Commentdto createComment(Commentdto commentdto, Integer pid,Integer id) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", pid));
Comment com=this.modelMapper.map(commentdto, Comment.class);
		com.setPost(post);
		User user=this.userrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
com.setUser(user);
	Comment saveComment=this.commentRepo.save(com);
		return this.modelMapper.map(saveComment, Commentdto.class);
	}

	@Override
	public void deleteComment(Integer id) {
		// TODO Auto-generated method stub
	Comment com=this.commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment id", id));
	this.commentRepo.delete(com);
	}

}
