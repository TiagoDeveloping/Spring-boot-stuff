package com.alien.SessionAuth.entities.post;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.SessionAuth.repos.PostRepository;

import javassist.NotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;
	
	public Post getById(Long id) throws NotFoundException {
		Optional<Post> p = postRepo.findById(id);
		if (p.isPresent()) {
			return p.get();
		} else {
			throw new NotFoundException("Couldn't find post");
		}
	}
	
	public void save(Post post) {
		postRepo.save(post);
	}
	
}
