package com.niit.collaborationbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.DAO.ForumDAO;
import com.niit.collaborationbackend.model.Forum;

@RestController
public class ForumController {

	@Autowired
	Forum forum;
	
	@Autowired
	ForumDAO forumDAO;
	
	//Get List Of ALL Forums
	@RequestMapping("/allForums")
	public ResponseEntity<List<Forum>> getAllForum()
	{
		List<Forum> forums = forumDAO.list();
		
		if(forums.isEmpty())
		{
			forum.setErrorCode("404");
			forum.setErrorMessage("No Forums Were Found");
			forums.add(forum);
		}
		
		return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
	}


	
	//Get Forum By Id
	@RequestMapping("/forumById/{id)")
	public ResponseEntity<Forum> getForumByID(@PathVariable("id") String forumId)
	{
		 forum = forumDAO.get(forumId);
		if(forum==null)
		{
			forum  = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("No Forums Were Found");
		}
		
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	
	//Save a new Forum
	@RequestMapping("/saveforum")
	public ResponseEntity<Forum> saveForum(@RequestBody Forum forum)
	{
		if(forumDAO.save(forum)==false)
		{
			forum.setErrorCode("404");
			forum.setErrorMessage("Forum was not Created.. !! ..!! .. Please Try Again After Some time..!!..!!..");
		}
		else
		{
			forum.setErrorCode("200");
			forum.setErrorMessage("Thank you !!..!!..Forum is Created SuccessFully");
		}
		
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	@RequestMapping("/updateForum")
	public ResponseEntity<Forum> updateForum(@RequestBody Forum forum)
	{
		if(forumDAO.update(forum)==false)
		{
			forum.setErrorCode("404");
			forum.setErrorMessage("Forum was not Updated.. !! ..!! .. Please Try Again After Some time..!!..!!..");
		}
		
		else
		{
			forum.setErrorCode("404");
			forum.setErrorMessage("Thank you !!..!!..Forum is updated SuccessFully");
		}
		
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	

}








