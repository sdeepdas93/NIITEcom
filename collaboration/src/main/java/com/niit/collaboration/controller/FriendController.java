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

import com.niit.collaboration.dao.FriendListDao;
import com.niit.collaboration.model.FriendList;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.User;

@RestController
public class FriendController {
	@Autowired 
	FriendListDao friendListDao;
	
	
	@PostMapping(value="/sendFriendRequest/{friendId}")
	public ResponseEntity<FriendList> sendFriendRequest(@PathVariable("friendId") String friendId,HttpSession session){
		try{
			User user=(User) session.getAttribute("loggedInUser");
			FriendList friendList=new FriendList();
			
			if((friendListDao.getFriendListifExistByUsers(user.getUserId(),friendId)!=null)||
				(friendList.getUserId().equals(user.getUserId()))){
				friendList.setErrorCode("404");
				friendList.setErrorMessage("FriendRequest Not Created");
				return new ResponseEntity<FriendList>(friendList,HttpStatus.OK);
				
			}
			friendList.setFriendListStatus("N");
			friendListDao.SavefriendList(friendList);
			return new ResponseEntity<FriendList>(friendList,HttpStatus.OK);
		}
		catch(NullPointerException e){
			FriendList friendList=new FriendList();
			friendList.setErrorCode("404");
			friendList.setErrorMessage("user Not logged in");
			return new ResponseEntity<FriendList>(friendList,HttpStatus.OK);
			
		}
		
		
		
		
		
		}
	
	@GetMapping(value="/showFriendRequests/")
	public ResponseEntity<List<FriendList>> showFirendRequests(HttpSession session){
		try{
			User user=(User)session.getAttribute("loggedInUser");
			System.out.println("at show friend requests userId ="+user.getUserId());
			
			return new ResponseEntity<List<FriendList>>(friendListDao.getAllFriendListByUserId(user.getUserId(), "N"),HttpStatus.OK);
		}catch(NullPointerException e){
			e.printStackTrace();
			return new ResponseEntity<List<FriendList>>(HttpStatus.NO_CONTENT);
		}
		
		
		
	}
	
	
	@PutMapping(value="/acceptFriendRequest")
	public ResponseEntity<FriendList> acceptFriendrequest(@RequestBody FriendList friendList,HttpSession session){
		
		try{
			User user=(User) session.getAttribute("loggedInUser");
			if((user.getUserId().equals(friendList.getFriendId()))||
			  (friendListDao.getFriendListByUsers(friendList.getUserId(), friendList.getFriendId(), "N").getFriendListId()==friendList.getFriendListId())){
				friendList.setFriendListStatus("A");
				friendListDao.updatefriendList(friendList);
				return new ResponseEntity<FriendList>(friendList,HttpStatus.OK);
			}else{
			
				friendList.setErrorCode("404");
				friendList.setErrorMessage("friend request not accepted");
				return new ResponseEntity<FriendList>(friendList,HttpStatus.OK);
				
			}
		}
		catch(NullPointerException e){
			friendList.setErrorCode("404");
			friendList.setErrorMessage("user Not logged in");
			return new ResponseEntity<FriendList>(friendList,HttpStatus.OK);
			
		}
		
	}
	
	
	@PutMapping(value="/rejectFriendRequest")
	public ResponseEntity<FriendList> rejectFriendrequest(@RequestBody FriendList friendList,HttpSession session){
		
		try{
			User user=(User) session.getAttribute("loggedInUser");
			if((user.getUserId().equals(friendList.getFriendId()))||
			  (friendListDao.getFriendListByUsers(friendList.getUserId(), friendList.getFriendId(), "N").getFriendListId()==friendList.getFriendListId())){
				friendListDao.deletefriendList(friendList);
				return new ResponseEntity<FriendList>(friendList,HttpStatus.OK);
			}else{
			
				friendList.setErrorCode("404");
				friendList.setErrorMessage("friend request not rejected");
				return new ResponseEntity<FriendList>(friendList,HttpStatus.OK);
				
			}
		}
		catch(NullPointerException e){
			friendList.setErrorCode("404");
			friendList.setErrorMessage("user Not logged in");
			return new ResponseEntity<FriendList>(friendList,HttpStatus.OK);
			
		}
		
	}
	
	
	@GetMapping(value="/showFriends")
	public ResponseEntity<List<FriendList>> showFriends(HttpSession session){
		try{
			User user=(User)session.getAttribute("loggedInUser");
			System.out.println("at show friend requests userId ="+user.getUserId());
			
			return new ResponseEntity<List<FriendList>>(friendListDao.getAllFriendListByUserId(user.getUserId(), "A"),HttpStatus.OK);
		}catch(NullPointerException e){
			e.printStackTrace();
			return new ResponseEntity<List<FriendList>>(HttpStatus.NO_CONTENT);
		}
	}
	
	

}
