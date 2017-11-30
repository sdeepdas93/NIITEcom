package com.niit.collaboration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.FriendListDao;

@RestController
public class FriendController {
	@Autowired 
	FriendListDao friendListDao;
	
	

}
