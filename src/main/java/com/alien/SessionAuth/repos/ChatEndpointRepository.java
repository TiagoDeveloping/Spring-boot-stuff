package com.alien.SessionAuth.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alien.SessionAuth.entities.chatendpoint.ChatEndpoint;

@Repository
public interface ChatEndpointRepository extends JpaRepository<ChatEndpoint, Long> {
	public ChatEndpoint findByUserid(Long id);
	public ChatEndpoint findByEndpoint(String endpoint);
	
}
