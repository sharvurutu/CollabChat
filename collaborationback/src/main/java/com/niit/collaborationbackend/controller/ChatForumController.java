package com.niit.collaborationbackend.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.collaborationbackend.model.Message;
import com.niit.collaborationbackend.model.OutputMessage;

@Controller
public class ChatForumController {
	public static Logger log = org.slf4j.LoggerFactory.getLogger(ChatForumController.class);
	
	@MessageMapping("/chat_forum")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message)
	{
		log.debug("ChatForumController ====> Starting of the sendMessage method()");

		log.debug("Message:-   ",message.getMessage());

		log.debug("ChatForumController ====> Ending of the sendMessage method()");

		return new OutputMessage(message, new Date());

	}
	
	
}
