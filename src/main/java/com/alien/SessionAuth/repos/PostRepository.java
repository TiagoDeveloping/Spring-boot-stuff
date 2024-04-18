package com.alien.SessionAuth.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.SessionAuth.entities.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	public List<Post> findByAuthor(Long id);
	public List<Post> findByTitle(String title);
	public Optional<Post> findById(Long id);
}
