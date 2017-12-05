package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.FriendList;

public interface FriendListDao {
	public boolean SavefriendList(FriendList friendList);
	public boolean updatefriendList(FriendList friendList);
	public boolean deletefriendList(FriendList friendList);
	public List<FriendList> getAllFriendListByUserId(String userId,String friendListStatus );
	public FriendList getFriendListByUsers(String userId1, String userId2,String friendListStatus);
	public FriendList getFriendListifExistByUsers(String userId1, String userId2);
	//public List<FriendList> getAllFriendRequestsbyUserId(String userId);
	

}
