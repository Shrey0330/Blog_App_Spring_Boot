package com.blog.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.config.AppConstants;
import com.blog.app.entities.Post;
import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.PostResponse;
import com.blog.app.payloads.Postdto;
import com.blog.app.services.FileService;
import com.blog.app.services.PostService;
import org.springframework.util.StreamUtils;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postservice;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{id}/category/{catId}/posts")
	public ResponseEntity<Postdto> createPost(@RequestBody Postdto postdto, @PathVariable Integer id,
			@PathVariable Integer catId) {

		System.out.println(id + " " + catId);
		Postdto post = this.postservice.createPost(postdto, id, catId);

		return new ResponseEntity<Postdto>(post, HttpStatus.CREATED);

	}

	// get by user
	@GetMapping("/user/{id}/posts")
	public ResponseEntity<List<Postdto>> getPostByUser(@PathVariable Integer id,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)

	{
		List<Postdto> postdto = this.postservice.getByUser(id, pageSize, pageNumber, sortBy, sortDir);

		return new ResponseEntity<List<Postdto>>(postdto, HttpStatus.OK);
	}

	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<Postdto>> getPostByCategory(@PathVariable Integer catId)

	{
		List<Postdto> postdto = this.postservice.getByCategory(catId);

		return new ResponseEntity<List<Postdto>>(postdto, HttpStatus.OK);
	}

	// getall
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,

			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		/* List<Postdto> postdto=this.postservice.getAllPost(pageNumber,pageSize); */
		PostResponse pr = this.postservice.getAllPost(pageSize, pageNumber, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(pr, HttpStatus.OK);
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<Postdto> getPostById(@PathVariable Integer id)

	{
		Postdto postdto = this.postservice.getPostById(id);

		return new ResponseEntity<Postdto>(postdto, HttpStatus.OK);
	}

	// update
	@PutMapping("/posts/{id}")
	public ResponseEntity<Postdto> updatePostById(@RequestBody Postdto postDto, @PathVariable Integer id)

	{
		Postdto postdto = this.postservice.updatePost(postDto, id);

		return new ResponseEntity<Postdto>(postdto, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/posts/{id}")
	public ApiResponse deletePost(@PathVariable Integer id) {
		this.postservice.deletePost(id);
		return new ApiResponse("Post is successfully deleted", true);
	}

	// search
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<Postdto>> searchByKeyword(@PathVariable String keyword)

	{
		List<Postdto> postdto = this.postservice.searchPosts(keyword);

		return new ResponseEntity<List<Postdto>>(postdto, HttpStatus.OK);
	}

	// post image upload
	@PostMapping("/posts/image/upload/{pid}")
	public ResponseEntity<Postdto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer pid) throws IOException
			 {
		Postdto postdto = this.postservice.getPostById(pid);
		String fileName = null;
		fileName = this.fileService.uploadImage(path, image);

		postdto.setImageName(fileName);
		Postdto updatedPost = this.postservice.updatePost(postdto, pid);
		return new ResponseEntity<Postdto>(updatedPost, HttpStatus.OK);
	}

	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException

	{
		InputStream resourse = this.fileService.getResourse(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourse, response.getOutputStream());
	}

}
