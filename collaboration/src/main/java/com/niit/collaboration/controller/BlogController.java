package com.niit.collaboration.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import com.niit.collaboration.dao.BlogLikeDao;
import com.niit.collaboration.dao.UserDao;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.BlogComment;
import com.niit.collaboration.model.BlogLike;
import com.niit.collaboration.model.User;



@RestController
public class BlogController {
	@Autowired
	BlogDao blogDao;
	@Autowired
	BlogCommentDao blogCommentDao;
	@Autowired
	UserDao userDao;
	@Autowired
	BlogLikeDao blogLikeDao;
	//Blog Section
	@GetMapping(value = "/blogs")
	public ResponseEntity<List<Blog>> listBlogs() {
		System.out.println("at blog list");
		List<Blog> blogs = blogDao.getAllBlogs();
		if (blogs.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		//ArrayList<Blog> reverseBlogList=(ArrayList<Blog>) blogs;
		Collections.reverse(blogs);
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value="/blog/")
	public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog,HttpSession session){
		System.out.println("at create blog");
		try{
		User user=(User) session.getAttribute("loggedInUser");
		
		if((user==null)||
			(blogDao.getBlogByBlogId(blog.getBlogId())!=null)){
			
			blog.setErrorCode("404");
			blog.setErrorMessage("BlogComment Not Created");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
				}
		
		blog.setBlogDate(new Date(System.currentTimeMillis()));
		blog.setBlogStatus("N");
		blog.setBlogCommentCount(0);
		blog.setUserId(user.getUserId());
		blogDao.saveBlog(blog);
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}catch(NullPointerException e){
			blog.setErrorCode("404");
			blog.setErrorMessage("User Not logged in");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			
		}
		}
	
	
	
	@GetMapping(value = "/blog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId") int blogId) {
		System.out.println("at get blog");
		Blog blog = blogDao.getBlogByBlogId(blogId);
		if (blog == null) {
			blog = new Blog();
			
			blog.setErrorMessage("No blog exist with id : " + blogId);

			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/likeBlog/{blogId}")
	public ResponseEntity<Blog> likeBlog(@PathVariable("blogId") int blogId,HttpSession session) {
		try{
			User user=(User) session.getAttribute("loggedInUser");
			System.out.println(user.getUserName());
			Blog blog = blogDao.getBlogByBlogId(blogId);
			if (blog == null) {
				blog = new Blog();
				
				blog.setErrorMessage("No blog exist with id : " + blogId);
	
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}else if(blogLikeDao.isExist(blogId, user.getUserId())){
			blog = new Blog();
			
			blog.setErrorMessage("User has already liked the blog: " + blogId);

			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		
			blog.setBlogCountLike(blog.getBlogCountLike()+1);
			blogDao.updateBlog(blog);
			BlogLike blogLike=new BlogLike();
			blogLike.setBlogId(blogId);blogLike.setUserId(user.getUserId());blogLike.setUserName(user.getUserName());
			blogLikeDao.saveBlogLike(blogLike);
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}catch(NullPointerException e){
			e.printStackTrace();
			Blog blog=new Blog();
			blog.setErrorMessage("not logged in");
			blog.setErrorCode("404");
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);

		}
		
		
	}
	
	@GetMapping(value = "/likeBlog/{blogId}")
	public ResponseEntity<List<BlogLike>> getBlogLikesByblogId(@PathVariable("blogId") int blogId,HttpSession session) {
		Blog blog = blogDao.getBlogByBlogId(blogId);
		if (blog == null) {
			return new ResponseEntity<List<BlogLike>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BlogLike>>(blogLikeDao.getBlogLikesByBlogId(blogId), HttpStatus.OK);
		
	}
	
	
	//Blog accept and rejecet
	
	@PutMapping(value="/approveBlog/{blogId}")
	ResponseEntity<Blog>approveBlog(@PathVariable("blogId") int blogId,HttpSession session)
	{	
		try{
			Blog blog=blogDao.getBlogByBlogId(blogId);
			if(((User)session.getAttribute("loggedInUser")).getUserRole().equals("ADMIN_USER")&&
					(blog!=null)){
						blog.setBlogStatus("A");
						blogDao.updateBlog(blog);
						return new ResponseEntity<Blog>(blog,HttpStatus.OK);
					}else{
						blog=new Blog();
						blog.setErrorCode("404");
						blog.setErrorMessage("blog not approved");
						return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
					}
		}catch(NullPointerException e){
			e.printStackTrace();
			Blog blog=new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("admin not loggedin");
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	@PutMapping(value="/rejectBlog/{blogId}")
	ResponseEntity<Blog>rejectBlog(@PathVariable("blogId") int blogId,HttpSession session)
	{	
		try{
			Blog blog=blogDao.getBlogByBlogId(blogId);
			if(((User)session.getAttribute("loggedInUser")).getUserRole().equals("ADMIN_USER")&&
					(blog!=null)){
						blog.setBlogStatus("R");
						blogDao.updateBlog(blog);
						return new ResponseEntity<Blog>(blog,HttpStatus.OK);
					}else{
						blog=new Blog();
						blog.setErrorCode("404");
						blog.setErrorMessage("blog not rejected");
						return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
					}
		}catch(NullPointerException e){
			e.printStackTrace();
			Blog blog=new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("admin not loggedin");
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	// BlogComment Section
	
	
	@GetMapping(value="/blogComments/{blogId}")
	public ResponseEntity<List<BlogComment>> getBlogComments(@PathVariable("blogId") int blogId,HttpSession session){
		
		System.out.println("at get blog comments");
		
		
		List<BlogComment> blogComments= blogCommentDao.getBlogCommentsByBlogId(blogId);
		if(blogComments==null){
			return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BlogComment>>(blogComments, HttpStatus.OK);
		
	}
	
	@PostMapping(value="/blogComment/")
	public ResponseEntity<BlogComment> saveBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		System.out.println("at save blogcomment");
		User user=(User) session.getAttribute("loggedInUser");
		
		//checking if the user doesnt exist or the blog doesnt exist
		Blog blog=blogDao.getBlogByBlogId(blogComment.getBlogId());
		
		if((user==null)||
			(blog==null)	
				){
			blogComment.setErrorCode("404");
			blogComment.setErrorMessage("BlogComment Not Created");
			return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}
		Date blogCommentDate=new Date(System.currentTimeMillis());
		
		blogComment.setBlogCommentDate(blogCommentDate);
		
		blogComment.setUserId(user.getUserId());
		blogComment.setUserName(user.getUserName());
		blogCommentDao.saveBlogComment(blogComment);
		
		blog.setBlogCommentCount(blog.getBlogCommentCount()+1);
		blogDao.updateBlog(blog);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		
	}
	
	

}
