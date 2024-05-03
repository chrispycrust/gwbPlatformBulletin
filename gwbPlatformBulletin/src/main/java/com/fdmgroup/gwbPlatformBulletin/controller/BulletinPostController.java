package com.fdmgroup.gwbPlatformBulletin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;
import com.fdmgroup.gwbPlatformBulletin.service.BulletinPostService;

@RestController
@RequestMapping("/bulletinBoard")
public class BulletinPostController {
	
	@Autowired
    private BulletinPostService bulletinPostService;
    
    @GetMapping
    public List<BulletinPost> getAllPosts() {
        return bulletinPostService.getAllPosts();
    }
    
    @GetMapping("/{id}")
    public Optional<BulletinPost> getPostById(@PathVariable(value = "id") Integer postId) {
        return bulletinPostService.getPostById(postId);
    }
    
    @PostMapping
    public BulletinPost createPost(@RequestBody BulletinPost post) {
        return bulletinPostService.createPost(post);
    }
    
    @PutMapping("/{id}")
    public BulletinPost updatePost(@PathVariable(value = "id") Integer postId, @RequestBody BulletinPost postDetails) {
        return bulletinPostService.updatePost(postId, postDetails);
    }
    
    @DeleteMapping("/{id}")
    public boolean deletePost(@PathVariable(value = "id") Integer postId) {
        return bulletinPostService.deletePost(postId);
    }

}
