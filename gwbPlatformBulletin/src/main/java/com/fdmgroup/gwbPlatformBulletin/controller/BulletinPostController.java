package com.fdmgroup.gwbPlatformBulletin.controller;

import java.util.List;
import java.util.Optional;

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

import com.fdmgroup.gwbPlatformBulletin.exceptions.ValidationException;
import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;
import com.fdmgroup.gwbPlatformBulletin.service.BulletinPostService;
import com.fdmgroup.gwbPlatformBulletin.utils.BulletinPostDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bulletinBoard")
public class BulletinPostController {
	
	@Autowired
    private BulletinPostService bulletinPostService;
	
	@PostMapping
    public BulletinPost createPost(@RequestBody BulletinPost post) {
		try {
            return bulletinPostService.createPost(post);
            
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
	
    @GetMapping
    public List<BulletinPost> getAllPosts() {
        return bulletinPostService.getAllPosts();
    }
    
    @GetMapping("/{id}")
    public Optional<BulletinPost> getPostById(@PathVariable(value = "id") Integer postId) {
        return bulletinPostService.getPostById(postId);
    }
    
    @GetMapping("/{authorSearch}")
    public Optional<List<BulletinPost>> getPostByAuthor(@PathVariable(value = "authorSearch") String authorSearch) {
    	
        return bulletinPostService.getPostByMemberName(authorSearch);
        
    }
    
    @PutMapping
    public void updatePost(@RequestBody BulletinPost post) {
        bulletinPostService.updatePost(post);
    }
    
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable(value = "id") Integer postId) {
        bulletinPostService.deleteById(postId);
    }

}
