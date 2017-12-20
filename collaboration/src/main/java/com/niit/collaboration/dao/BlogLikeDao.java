package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.BlogLike;

public interface BlogLikeDao {
	public List<BlogLike>getBlogLikesByBlogId(int blogId);
	public boolean saveBlogLike(BlogLike blogLike);
	public boolean isExist(int blogId,String userId);
	
}
