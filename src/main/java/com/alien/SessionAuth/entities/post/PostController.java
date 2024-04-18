package com.alien.SessionAuth.entities.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alien.SessionAuth.entities.user.CustomUserDetails;

import javassist.NotFoundException;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;

	@GetMapping("/view")
	public String viewPosts(@RequestParam("id") Long id, Model model) {
		try {
            Post post = postService.getById(id);
            model.addAttribute("post", post);
        } catch (NotFoundException e) {
            model.addAttribute("post", null);
        }
		return "view_post";
	}
	
	@GetMapping("/create_form")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "createPost_form";
    }
	
	@PostMapping("/create")
    public String createPost(@ModelAttribute("post") Post post) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
        Long id = cud.getId();
		
		post.setAuthor(id);
        postService.save(post);
        return "redirect:/post/view?id=" + post.getId();
    }
	
}
