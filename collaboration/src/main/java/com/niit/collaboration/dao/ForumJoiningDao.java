package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.ForumJoining;

public interface ForumJoiningDao {
	public boolean saveForumJoining(ForumJoining forumJoining);
	public boolean isExist(String userId,int forumId);
	public List<ForumJoining> getForumJoiningsByUserId(String userId);
	public List<ForumJoining> getForumJoiningsByForumId(int forumId);
}

