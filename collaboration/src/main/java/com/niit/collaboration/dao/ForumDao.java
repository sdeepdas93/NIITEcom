package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Forum;



public interface ForumDao {
	public List<Forum>getAllForums();
	public boolean saveForum(Forum forum);
	public boolean deleteForum(Forum forum);
	public boolean updateForum(Forum forum);
	public Forum getForumByForumId(int forumId);

}
