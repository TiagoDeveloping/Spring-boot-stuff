package com.alien.SessionAuth.chatsocket;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.alien.SessionAuth.entities.chatendpoint.ChatEndpointService;
import com.alien.SessionAuth.entities.user.CustomUserDetails;

@Configuration
@EnableWebSocket
public class ChatSocketConfig implements WebSocketConfigurer {

	@Autowired
	private ChatSocketHandler csHandler;

	@Autowired
	private ChatEndpointService ceService;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(csHandler, "/chats/{id}").addInterceptors(new HandshakeInterceptor() {

			@Override
			public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
				if (request.getPrincipal() instanceof Authentication) {
					Authentication authentication = (Authentication) request.getPrincipal();

					if (authentication == null || !authentication.isAuthenticated()) {
						return false;
					}

					Object principal = authentication.getPrincipal();
					if (!(principal instanceof CustomUserDetails)) {
						return false;
					}

					CustomUserDetails userDetails = (CustomUserDetails) principal;
					String userEndpoint = ceService.getEndpointForUser(userDetails.getId());

					String currentEndpoint = request.getURI().toString().substring(
							request.getURI().toString().lastIndexOf("/") + 1, request.getURI().toString().length());

					System.out.println("FROM HANDSHAKE HANDLER: " + "user: " + userEndpoint + " - current: "
							+ currentEndpoint + " => " + (userEndpoint.equals(currentEndpoint)));

					if (userEndpoint.equals(currentEndpoint)) {
						return true;
					} else {
						return false;
					}
				}
				return false;

			}

			@Override
			public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler wsHandler, Exception exception) {
				// TODO Auto-generated method stub

			}

		});
	}
}