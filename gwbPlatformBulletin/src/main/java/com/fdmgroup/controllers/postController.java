package com.fdmgroup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bulletinBoard")
public class postController {
	
	@Autowired
    private PostService postService;
    
    // GET all posts
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    // GET a post by ID
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id") int postId) {
        Post post = postService.getPostById(postId);
        if(post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(post);
    }
    
    // POST a new post
    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postService.createPost(post);
    }
    
    // PUT (update) a post
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable(value = "id") int postId, @Valid @RequestBody Post postDetails) {
        Post updatedPost = postService.updatePost(postId, postDetails);
        if(updatedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedPost);
    }
    
    // DELETE a post
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") int postId) {
        if(postService.deletePost(postId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
