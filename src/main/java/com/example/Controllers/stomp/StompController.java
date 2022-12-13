package com.example.Controllers.stomp;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import com.example.NonDbEntities.Greeting;
import com.example.NonDbEntities.Message;
import com.example.Security.configuration.MyUserDetails;

@Controller
public class StompController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/hello")
	public void returnResponse(Message messageFromClient, Authentication principal) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		MyUserDetails userDetails = (MyUserDetails) principal.getPrincipal();
		Greeting newGreeting = new Greeting(userDetails.getCustomer().getUser().getUsername()+" says "+messageFromClient.getName());
		simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/messages", newGreeting);
		simpMessagingTemplate.convertAndSendToUser(messageFromClient.getName(), "/queue/messages", newGreeting);
	}
}
