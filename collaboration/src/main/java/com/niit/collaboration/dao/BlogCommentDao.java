package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.BlogComment;

public interface BlogCommentDao {
	public List<BlogComment>getAllBlogComments();
	public boolean saveBlogComment(BlogComment blogComment);
	public boolean deleteBlogComment(BlogComment blogComment);
	public boolean updateBlogComment(BlogComment blogComment);
	public BlogComment getBlogCommentByBlogCommentId(int blogCommentId);
	public List<BlogComment> getBlogCommentsByBlogId(int blogId);

}
