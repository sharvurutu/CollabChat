package com.niit.collaborationbackend.DAO;

import java.util.List;

import com.niit.collaborationbackend.model.Blog;

public interface BlogDAO {
	
	public boolean save(Blog blog);
	
	public Blog get(int id);

	public Blog getById(int id);

	
	public boolean delete(Blog blog);
	
	public boolean update(Blog blog);
	
	public List<Blog> list();
	
	public Integer maxID();


}
