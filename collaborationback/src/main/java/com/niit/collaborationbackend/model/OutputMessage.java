package com.niit.collaborationbackend.model;

import java.util.Date;

import org.slf4j.Logger;

public class OutputMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static Logger log = org.slf4j.LoggerFactory.getLogger(OutputMessage.class);

	
	private Date time;
	
	public OutputMessage(Message message, Date time) {
		super(message.getMessage());
		this.time = time;
		log.debug("OutputMessage ====> Ending of the OutputMessage constructor()");

		// TODO Auto-generated constructor stub
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	

}
