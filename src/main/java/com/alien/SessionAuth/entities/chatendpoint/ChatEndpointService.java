package com.alien.SessionAuth.entities.chatendpoint;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.SessionAuth.repos.ChatEndpointRepository;

@Service
public class ChatEndpointService {

	@Autowired
	private ChatEndpointRepository ceRepo;
	
	public String getEndpointForUser(Long userId) {
		ChatEndpoint endpoint = ceRepo.findByUserid(userId);
		
		if (null == endpoint) {
			ChatEndpoint newEndpoint = ChatEndpoint.builder().userid(userId).endpoint(UUID.randomUUID().toString()).build();
			save(newEndpoint);
			return newEndpoint.getEndpoint();
		} else {
			return endpoint.getEndpoint();
		}
		
	}
	
	public Long getUserForEndpoint(String endpoint) {
		return ceRepo.findByEndpoint(endpoint).getUserid();
	}
	
	public void remove(String endpoint) {
		ChatEndpoint ep = ceRepo.findByEndpoint(endpoint);
		ceRepo.delete(ep);
	}
	
	public void save(ChatEndpoint ep) {
		ceRepo.save(ep);
	}
}
