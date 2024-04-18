package com.alien.SessionAuth.chatsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alien.SessionAuth.entities.chatendpoint.ChatEndpointService;

@Component
public class ChatSocketHandler extends TextWebSocketHandler {

	@Autowired
    private ChatEndpointService ceService;
	
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	System.out.println("connection established for: " + session.toString());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming WebSocket messages
    }
}
