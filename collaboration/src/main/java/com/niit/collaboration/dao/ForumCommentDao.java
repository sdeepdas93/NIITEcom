package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.ForumComment;

public interface ForumCommentDao {
	public List<ForumComment>getAllForumComments();
	public boolean saveForumComment(ForumComment forumComment);
	public boolean deleteForumComment(ForumComment forumComment);
	public boolean updateForumComment(ForumComment forumComment);
	public ForumComment getForumCommentByForumCommentId(int forumCommentId);
	public List<ForumComment> getForumCommentsByForumId(int forumId);

}
