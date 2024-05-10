package com.fdmgroup.gwbPlatformBulletin.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fdmgroup.gwbPlatformBulletin.exceptions.AuthorizationException;
import com.fdmgroup.gwbPlatformBulletin.exceptions.NonexistentPostException;
import com.fdmgroup.gwbPlatformBulletin.exceptions.ValidationException;
import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;
import com.fdmgroup.gwbPlatformBulletin.service.BulletinPostService;
import com.fdmgroup.gwbPlatformBulletin.service.MemberService;

import com.fdmgroup.gwbPlatformBulletin.model.Member;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bulletinBoard")
public class BulletinPostController {
	
    private BulletinPostService bulletinPostService;
	
	private MemberService memberService;
	
//	private TokenService tokenService;
	
	
	@Autowired
	public BulletinPostController(BulletinPostService bulletinPostService, MemberService memberService) {
		super();
		this.bulletinPostService = bulletinPostService;
		this.memberService = memberService;
//		this.tokenService = tokenService;
	}


	/**
	 * @Valid ensures posts are validated according to constraints in BulletinPost
	 * 
	 * @param post
	 * @return BulletinPost
	 */
	@PostMapping
    public BulletinPost createPost(@Valid @RequestBody BulletinPost post, Authentication authentication) {
		
		try {
			
			String username = authentication.getName(); 
			Optional<Member> optionalMember = memberService.findMemberByEmail(username);
	        if (!optionalMember.isPresent()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found");
	        }
	    
	        Member member = optionalMember.get();
			post.setAuthor(member);
			
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
    
    @GetMapping("/author/{authorId}")
    public List<BulletinPost> getPostByAuthorId(@PathVariable(value = "authorId") Integer authorId) {
    	return bulletinPostService.findByAuthorId(authorId);
    }

//    @GetMapping("/search/{searchTerm}")
//    public List<BulletinPost> getPostBySearch(@PathVariable(value = "searchTerm") String searchTerm) {
//    	
//        return bulletinPostService.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(searchTerm);
//        
//    }
    
//    @PutMapping("/{id}")
//    public void updatePost(@PathVariable Integer id, 
//    		String authorEmail, 
//    		String updatedTitle, 
//    		String updatedContent, Authentication authentication) throws NonexistentPostException, AuthorizationException {
//    	
//    	System.out.println("postid: " + id);
//    	System.out.println("authorEmail: " + authorEmail);
//    	System.out.println("updatedTitle: " + updatedTitle);
//    	System.out.println("updatedContent: " + updatedContent);
//
//    	bulletinPostService.updatePost(id, authorEmail, updatedTitle, updatedContent, authentication);
//    }
    
    @PutMapping("/{id}")
    public void updatePost(@PathVariable Integer id, @Valid @RequestBody BulletinPost updatedPost, Authentication authentication) 
    		throws NonexistentPostException, AuthorizationException {
    	
    	bulletinPostService.getPostById(id);
    	
    	bulletinPostService.updatePost(id, updatedPost, authentication);

    }
    
    
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable(value = "id") Integer postId) {
    	
    	boolean deleted = bulletinPostService.deleteById(postId);
    	
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with id: " + postId);
        }

    }

}
