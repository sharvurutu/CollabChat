package com.niit.collaborationbackend.DAO;

import java.util.List;

import com.niit.collaborationbackend.model.BlogComment;
import com.niit.collaborationbackend.model.BlogPost;
import com.niit.collaborationbackend.model.User;


public interface BlogDAO1 {
	List<BlogPost> getBlogPosts();
	BlogPost getBlogPost(int id);
	BlogPost addBlogPost(User user,BlogPost blogPost);
	List<BlogComment> getBlogComments(int blogId);
	BlogPost addBlogPostComment(User user,BlogComment blogComment);
}



