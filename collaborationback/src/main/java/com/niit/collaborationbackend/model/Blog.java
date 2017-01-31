package com.niit.collaborationbackend.model;

import java.util.Date;
import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
@Table(name="f_Blog")
@Component
public class Blog extends BaseDomain {
	
	@Id
	private int Id;
	
	private String tittle;
	
	private String emailId;
	
	private Date date_Time;
	
	private char status;
	
	private String reason;
	
	private String description;
	

	public int getId() {
		return Id;
		
	}
	

	public void setId(int id) {
		this.Id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDate_Time() {
		return date_Time;
	}

	public void setDate_Time(Date date_Time) {
		this.date_Time = date_Time;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

		
	
	

}
