package com.niit.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.BlogDao;
import com.niit.collaboration.model.Blog;



@RestController
public class BlogController {
	@Autowired
	BlogDao blogDao;
	
	@GetMapping(value = "/blogs")
	public ResponseEntity<List<Blog>> listBlogs() {

		List<Blog> blogs = blogDao.getAllBlogs();
		if (blogs.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/blog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId") int blogId) {

		Blog blog = blogDao.getBlogByBlogId(blogId);
		if (blog == null) {
			blog = new Blog();
			
			blog.setErrorMessage("No blog exist with id : " + blogId);

			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

}
