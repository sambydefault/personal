package com.boot.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.boot.model.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat.addUser/{roomId}")
	@SendTo("/topic/greetings/{roomId}")
	public ChatMessage connectToChatRoom(@DestinationVariable String roomId, @Payload ChatMessage chatMessage,
			SimpMessageHeaderAccessor headerAccessor) {
		// User user = userService.findExistingUser(userRepository,
		// chatMessage.getRecipient());
		System.out.println(chatMessage.getRecipient() + " " + chatMessage.getContent());
		return chatMessage;
	}
}
