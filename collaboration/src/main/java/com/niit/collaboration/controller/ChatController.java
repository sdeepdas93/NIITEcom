package com.niit.collaboration.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.collaboration.model.Message;
import com.niit.collaboration.model.OutputMessage;
import com.niit.collaboration.model.User;



@Controller
public class ChatController {

	//Logger log = Logger.getLogger(ChatController.class);
	
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message) {
		System.out.println("Calling the method sendMessage().");
		
		
	//	message.setUserId(userId);
		System.out.println("Message : "+message.getMessage());
		
		System.out.println("Message ID : "+message.getId());
System.out.println("Message userId: "+message.getUserId());
				
		return new OutputMessage(message, new Date());

	}
	
}
