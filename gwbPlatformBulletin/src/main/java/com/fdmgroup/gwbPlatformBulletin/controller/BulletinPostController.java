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

import com.fdmgroup.gwbPlatformBulletin.exceptions.NonexistentPostException;
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
	
	/**
	 * @Valid ensures posts are validated according to constraints in BulletinPost
	 * 
	 * @param post
	 * @return BulletinPost
	 */
	@PostMapping
    public BulletinPost createPost(@Valid @RequestBody BulletinPost post) {
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
    public BulletinPost getPostById(@PathVariable(value = "id") Integer postId) {
    	return bulletinPostService.getPostById(postId)
    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with id: " + postId));
    }
    
    // get post by author id?
    
//    @GetMapping("/author/{authorSearch}")
//    public List<BulletinPost> getPostByAuthor(@PathVariable(value = "authorSearch") String authorSearch) {
//    	
//        return bulletinPostService.getPostByAuthor(authorSearch);
//        
//    }

    @GetMapping("/search/{searchTerm}")
    public List<BulletinPost> getPostBySearch(@PathVariable(value = "searchTerm") String searchTerm) {
    	
        return bulletinPostService.findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrContentContainingIgnoreCase(searchTerm);
        
    }
    
    @PutMapping("/{id}")
    public void updatePost(@PathVariable Integer id, @Valid @RequestBody BulletinPost post) throws NonexistentPostException {
    	bulletinPostService.updatePost(id, post);
    }
    
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable(value = "id") Integer postId) {
    	
    	boolean deleted = bulletinPostService.deleteById(postId);
    	
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with id: " + postId);
        }

    }

}
