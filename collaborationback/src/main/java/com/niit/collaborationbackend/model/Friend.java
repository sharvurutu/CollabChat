package com.niit.collaborationbackend.model;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
@Table(name="f_friend")
@Component
public class Friend extends BaseDomain {
	
	@Id
	private int Id;
	
	private String username;
	
	private String friendUserName;
	
	private char status;
	
	@Column(name="IS_ONLINE")
	private char isOnline;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFriendUserName() {
		return friendUserName;
	}

	public void setFriendUserName(String friendUserName) {
		this.friendUserName = friendUserName;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}
	
	
	

}
