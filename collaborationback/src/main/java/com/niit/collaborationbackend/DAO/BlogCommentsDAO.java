package com.niit.collaborationbackend.DAO;

import java.util.List;

import com.niit.collaborationbackend.model.BlogComments;

public interface BlogCommentsDAO {
	
	public boolean postComment(BlogComments blogComments);
	
	public List<BlogComments> allBlogComments();

	public List<BlogComments> blogComment(int blogId);

	
	public Integer maxID();


}
