package com.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.Commentdto;
import com.blog.app.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@PostMapping("/comment/{pid}/{id}")
public ResponseEntity<Commentdto> createComment(@RequestBody Commentdto comment,@PathVariable("pid") Integer pid,@PathVariable("id") Integer id)
{
	Commentdto cretedComment=this.commentService.createComment(comment, pid,id);
		return new ResponseEntity<Commentdto>(cretedComment, HttpStatus.CREATED);
	
}
	@DeleteMapping("/comment/{id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("id") Integer id)
	{
this.commentService.deleteComment(id);
			return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted", true), HttpStatus.OK);
		
	}
}
