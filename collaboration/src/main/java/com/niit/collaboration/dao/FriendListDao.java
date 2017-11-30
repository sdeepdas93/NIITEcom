package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.FriendList;

public interface FriendListDao {
	public boolean SavefriendList(FriendList friendList);
	public boolean updatefriendList(FriendList friendList);
	public boolean deletefriendList(FriendList friendList);
	public List<FriendList> getAllFriendListByUserId(String userId);
	//public List<FriendList> getAllFriendRequestsbyUserId(String userId);
	

}
