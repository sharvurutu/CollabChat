package com.niit.collaborationbackend.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
@Table(name="f_Job_Applied")
@Component
public class JobApplication extends BaseDomain {
	
	@Id
	private int Id;
	
	private String username;
	
	private String jobId;

	private Date date_Applied;
	
	private char status;
	
	private String remarks;

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

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getDate_Applied() {
		return date_Applied;
	}

	public void setDate_Applied(Date date_Applied) {
		this.date_Applied = date_Applied;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
}
