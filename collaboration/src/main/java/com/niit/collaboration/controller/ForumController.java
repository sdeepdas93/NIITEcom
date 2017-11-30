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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.ForumCommentDao;
import com.niit.collaboration.dao.ForumDao;
import com.niit.collaboration.dao.UserDao;
import com.niit.collaboration.model.BlogComment;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.ForumComment;
import com.niit.collaboration.model.User;


@RestController
public class ForumController {
	
	@Autowired
	ForumDao forumDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ForumCommentDao forumCommentDao;
	
	
	@GetMapping(value = "/forums")
	public ResponseEntity<List<Forum>> listForums(HttpSession session) {
		try{
		User user=(User) session.getAttribute("loggedInUser");
		if(user==null)  return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		List<Forum> forums = forumDao.getAllForums();
		if (forums.isEmpty()) {
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}
		

		return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
		}catch(NullPointerException e){
			System.out.println("user not logged in");
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}
			
			
	}
	
	
	
	
	@PostMapping(value="/forum/")
	public ResponseEntity<Forum> saveForum(@RequestBody Forum forum,HttpSession session){
		try{
		User user=(User) session.getAttribute("loggedInUser");
		
		if((!(forum.getForumCreaterId().equals(user.getUserId()))||
			(forumDao.getForumByForumId(forum.getForumId())!=null))){
			
			forum.setErrorCode("404");
			forum.setErrorMessage("Forum Not Created");
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
				}
		}catch(NullPointerException e){
			forum.setErrorCode("404");
			forum.setErrorMessage("User Not logged in");
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
			
		}
		forum.setForumCreationDate(new Date(System.currentTimeMillis()));
		forum.setForumStatus("A");
		forumDao.saveForum(forum);
		
		
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
	
	
	
	@GetMapping(value = "/forum/{forumId}")
	public ResponseEntity<Forum> getForum(@PathVariable("forumId") int forumId,HttpSession session) {
		try{
			User user=(User) session.getAttribute("loggedInUser");
			System.out.println(user.getUserId());
		}catch(NullPointerException e){
			e.printStackTrace();
			Forum forum=new Forum();
			forum.setErrorMessage("user not lgged in");
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		Forum forum=forumDao.getForumByForumId(forumId);
		
		if (forum == null) {
			forum = new Forum();
			
			forum.setErrorMessage("No Forum exist with id : " + forumId);

			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	
	
	// ForumComment Section
	
	
		@GetMapping(value="/forumComments/{forumId}")
		public ResponseEntity<List<ForumComment>> getForumComments(@PathVariable("forumId") int forumId,HttpSession session){
			
			User user=(User) session.getAttribute("loggedInUser");
			
			
			
			List<ForumComment> forumComments= forumCommentDao.getForumCommentsByForumId(forumId);
			if((forumComments==null)||(user==null)){
				return new ResponseEntity<List<ForumComment>>(forumComments,HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<ForumComment>>(forumComments, HttpStatus.OK);
			
		}
		
		@PostMapping(value="/forumComment/")
		public ResponseEntity<ForumComment> saveBlogComment(@RequestBody ForumComment forumComment,HttpSession session){
			User user=(User) session.getAttribute("loggedInUser");
			try{
			//checking if the user doesnt exist or the blog doesnt exist
				if((!(user.getUserId().equals(forumComment.getUserId())))||
					(forumDao.getForumByForumId(forumComment.getForumId()))==null)	
						{
							forumComment.setErrorCode("404");
							forumComment.setErrorMessage("forumcomment Not Created");
							return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
				}
			}catch(NullPointerException e){
				e.printStackTrace();
				forumComment.setErrorCode("404");
				forumComment.setErrorMessage("user not loggedin");
				return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
				
			}
			Date forumCommentDate=new Date(System.currentTimeMillis());
			
			forumComment.setForumCommentDate(forumCommentDate);
			forumCommentDao.saveForumComment(forumComment);
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
			
		}
		
	
}


