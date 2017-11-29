package com.niit.collaboration.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.BlogCommentDao;
import com.niit.collaboration.dao.BlogDao;
import com.niit.collaboration.dao.UserDao;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.BlogComment;
import com.niit.collaboration.model.User;



@RestController
public class BlogController {
	@Autowired
	BlogDao blogDao;
	@Autowired
	BlogCommentDao blogCommentDao;
	@Autowired
	UserDao userDao;
	//Blog Section
	@GetMapping(value = "/blogs")
	public ResponseEntity<List<Blog>> listBlogs() {

		List<Blog> blogs = blogDao.getAllBlogs();
		if (blogs.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	@PostMapping(value="/blog/")
	public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog,HttpSession session){
		try{
		User user=(User) session.getAttribute("loggedInUser");
		
		if((!(blog.getUserId().equals(user.getUserId()))||
			(blogDao.getBlogByBlogId(blog.getBlogId())!=null))){
			
			blog.setErrorCode("404");
			blog.setErrorMessage("BlogComment Not Created");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
				}
		}catch(NullPointerException e){
			blog.setErrorCode("404");
			blog.setErrorMessage("User Not logged in");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			
		}
		blog.setBlogDate(new Date(System.currentTimeMillis()));
		blog.setBlogStatus("A");
		blogDao.saveBlog(blog);
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
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
	
	
	@PutMapping(value = "/likeBlog/{blogId}")
	public ResponseEntity<Blog> likeBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDao.getBlogByBlogId(blogId);
		if (blog == null) {
			blog = new Blog();
			
			blog.setErrorMessage("No blog exist with id : " + blogId);

			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		
		blog.setBlogCountLike(blog.getBlogCountLike()+1);
		blogDao.updateBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		
		
	}
	
	
	
	// BlogComment Section
	
	
	@GetMapping(value="/blogComments/{blogId}")
	public ResponseEntity<List<BlogComment>> getBlogComments(@PathVariable("blogId") int blogId,HttpSession session){
		
		
		
		
		List<BlogComment> blogComments= blogCommentDao.getBlogCommentsByBlogId(blogId);
		if(blogComments==null){
			return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BlogComment>>(blogComments, HttpStatus.OK);
		
	}
	
	@PostMapping(value="/blogComment/")
	public ResponseEntity<BlogComment> saveBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		User user=(User) session.getAttribute("loggedInUser");
		
		//checking if the user doesnt exist or the blog doesnt exist
		if((!(user.getUserId().equals(blogComment.getUserId())))||
			(blogDao.getBlogByBlogId(blogComment.getBlogId())==null)	
				){
			blogComment.setErrorCode("404");
			blogComment.setErrorMessage("BlogComment Not Created");
			return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}
		Date blogCommentDate=new Date(System.currentTimeMillis());
		
		blogComment.setBlogCommentDate(blogCommentDate);
		blogCommentDao.saveBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		
	}
	
	

}
