package com.niit.collaborationbackend.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Forum extends BaseDomain{

	private int Id;
	
	private String emailId;
	
	private String message;
	
	private Date Date_Time;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate_Time() {
		return Date_Time;
	}

	public void setDate_Time(Date date_Time) {
		Date_Time = date_Time;
	}
	
	
}
