package com.niit.collaborationbackend.DAO;

import java.util.List;

import com.niit.collaborationbackend.model.User;

public interface UserDAO {

	public boolean save(User user);
	
	public boolean delete(User user);
	
	public boolean update(User user);
	
	public User get(String emaild);
	
	public User IsValidUser(String emailId, String password);
	
	public List<User> list();	
	
	public void setOnline(String emailId);

	public void setOffline(String emailId);

	public List<User> getall(String username);


}
