package com.niit.collaborationbackend.DAO;

import java.util.List;

import com.niit.collaborationbackend.model.Friend;

public interface FriendDAO {
	
	public boolean save(Friend friend);
	
	public boolean delete(Friend friend);
	
	public boolean update(Friend friend);

	public List<Friend> getMyFriends(String friendEmaildId);
	
	public List<Friend> getMyFriendRequests(String emailId);
	
	
	//public void delete(String emailId, String friendEmailID);
	
	public Friend get(String emailId, String friendEmailID);
	
	public void setOnline(String emailId);

	public void setOffline(String emailId);
	
	public List<Friend> getMySentFriendRequest(String emailId);

	
	public Integer maxID();


	
	
	
	}
