package com.alien.SessionAuth.entities.chatendpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alien.SessionAuth.entities.user.CustomUserDetails;

@Controller
@RequestMapping("/chat")
public class ChatEndpointController {

	@Autowired
	private ChatEndpointService ceService;
	
	@GetMapping("/")
	public String chat(Model model) {
		return "chat";
	}
	
	@GetMapping("/getEndpoint")
	@ResponseBody
	public String getWebsocketEndpoint() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        Long id = cud.getId();
		
		String ep = ceService.getEndpointForUser(id);
		
		// open websocket endpoint /chats/ep
		
		System.out.println("at get endpoint: " + id);
		
		return ep;
	}
	
	@PostMapping("/closeEndpoint")
	public void closeWebsocketEndpoint(@RequestParam("endpoint") String endpoint) {
		ceService.remove(endpoint);
	}
	
}
