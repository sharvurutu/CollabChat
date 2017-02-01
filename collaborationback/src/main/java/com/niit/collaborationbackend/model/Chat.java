package com.niit.collaborationbackend.model;

import java.util.Date;

public class Chat {
	
	private int Id;
	
	private String emailId;
	
	private String friendEmailId;
	
	private String Message;
	
	private Date date;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFriendEmailId() {
		return friendEmailId;
	}

	public void setFriendEmailId(String friendEmailId) {
		this.friendEmailId = friendEmailId;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
