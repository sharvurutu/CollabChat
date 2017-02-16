package com.niit.collaborationbackend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.DAO.BlogCommentsDAO;
import com.niit.collaborationbackend.DAO.BlogDAO;
import com.niit.collaborationbackend.model.Blog;
import com.niit.collaborationbackend.model.BlogComments;

@RestController
public class BlogController {

	@Autowired
	Blog blog;

	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	BlogCommentsDAO blogCommentDAO;
	
	@Autowired
	BlogComments blogComments;

	@Autowired(required = false)
	HttpSession session;

	// Get List Of ALL Blogs
	@RequestMapping(value = "/allBlogs", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getAllBlog() {
		List<Blog> blogs = blogDAO.list();
		if (blogs.isEmpty()) {
			blog.setErrorCode("404");
			blog.setErrorMessage("No Blogs Were Found");
			blogs.add(blog);
		}

		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}

	// Get Blog By Id
	@RequestMapping(value = "/blogById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Blog> getBlogByID(@PathVariable("id") int blogId) {
		
		blog = blogDAO.get(blogId);
		if (blog == null) {
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("No Blogs Were Found");
		}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	// Save a new Blog
	@RequestMapping(value = "/saveBlog/", method = RequestMethod.POST)
	public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) {
		System.out.println(blog.getId());		

		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		if (loggedInUserId == null) {
			blog.setErrorCode("404");
			blog.setErrorMessage("Please login to Create Blog");
		} 
		
		else {
			System.out.println(blog.getId());		
			System.out.println(blog.getTittle());

		//	Integer blogId = blogDAO.maxID();
			blog.setId(blogDAO.maxID());
			blog.setEmailId(loggedInUserId);
			blog.setDate_Time(new Date());
			blog.setStatus('N');
			blog.setReason("New");
			if (blogDAO.save(blog) == false) {
				blog.setErrorCode("404");
				blog.setErrorMessage("Blog was not Created.. !! ..!! .. Please Try Again After Some time..!!..!!..");
			} else {
				blog.setErrorCode("200");
				blog.setErrorMessage("Thank you !!..!!..Blog is Created SuccessFully");
			}
		}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping("/updateBlog")
	public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
		if (blogDAO.update(blog) == false) {
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog was not Updated.. !! ..!! .. Please Try Again After Some time..!!..!!..");
		}

		else {
			blog.setErrorCode("404");
			blog.setErrorMessage("Thank you !!..!!..Blog is updated SuccessFully");
		}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/postBlogComment/{blogId}/{comment}", method = RequestMethod.POST)
	public ResponseEntity<Blog> postBlogComment(@PathVariable("blogId") Integer blogId,@PathVariable("comment") String comment) {
		System.out.println(blog.getId());		

		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		if (loggedInUserId == null) {
			blog.setErrorCode("404");
			blog.setErrorMessage("Please login to Create Blog");
		} 
		
		else {
		//	System.out.println(blog.getId());		
			//System.out.println(blog.getTittle());

		//	Integer blogId = blogDAO.maxID();
			blogComments.setId(blogCommentDAO.maxID());
			blogComments.setUsername(loggedInUserId);
			blogComments.setBlogId(blogId);
			blogComments.setUserComment(comment);
			if (blogCommentDAO.postComment(blogComments)== false) {
				blogComments.setErrorCode("404");
				blogComments.setErrorMessage("BlogComment was not updated.. !! ..!! .. Please Try Again After Some time..!!..!!..");
			} else {
				blogComments.setErrorCode("200");
				blogComments.setErrorMessage("Thank you !!..!!..BlogComment is posted SuccessFully");
			}
		}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/allBlogComments", method = RequestMethod.GET)
	public ResponseEntity<List<BlogComments>> allBlogComments () {
		List<BlogComments> allblogComments = blogCommentDAO.allBlogComments();
		if (allblogComments.isEmpty()) {
			blogComments.setErrorCode("404");
			blogComments.setErrorMessage("No Blogs Were Found");
			allblogComments.add(blogComments);
		}

		return new ResponseEntity<List<BlogComments>>(allblogComments, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/blogComments/{blogId}", method = RequestMethod.GET)
	public ResponseEntity<List<BlogComments>> BlogComments (@PathVariable("blogId") Integer blogId) {
		List<BlogComments> allblogComments = blogCommentDAO.blogComment(blogId);
		if (allblogComments.isEmpty()) {
			blogComments.setErrorCode("404");
			blogComments.setErrorMessage("No Blogs Were Found");
			allblogComments.add(blogComments);
		}

		return new ResponseEntity<List<BlogComments>>(allblogComments, HttpStatus.OK);
	}

	
}
