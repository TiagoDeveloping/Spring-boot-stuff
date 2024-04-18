package com.alien.SessionAuth.entities.chatendpoint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chat_endpoints")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatEndpoint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(nullable = false, unique = true)
	private Long userid;
    
    @Column(nullable = false, unique = true)
    private String endpoint;
	
}
