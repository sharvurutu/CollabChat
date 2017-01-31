package com.niit.collaborationbackend.DAO;

import java.util.List;

import com.niit.collaborationbackend.model.Forum;

public interface ForumDAO {
	
	public boolean save(Forum forum);
	
	public boolean delete(Forum forum);
	
	public boolean update(Forum forum);
	
	public Forum get(String id);
	
	public List<Forum> list();
	

}
