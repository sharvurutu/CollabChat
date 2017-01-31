package com.niit.collaborationbackend.model;

import java.io.Serializable;

import org.slf4j.Logger;


public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static Logger log = org.slf4j.LoggerFactory.getLogger(Message.class);

	
	String message;
	
	public Message()
	{}
	
	public Message(String message)
	{
		this.message=message;
		
		log.debug("Message ====> Starting of the Message constructor()");

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
