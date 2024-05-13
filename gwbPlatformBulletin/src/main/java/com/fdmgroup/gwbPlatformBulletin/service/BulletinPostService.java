package com.fdmgroup.gwbPlatformBulletin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.gwbPlatformBulletin.exceptions.AuthorizationException;
import com.fdmgroup.gwbPlatformBulletin.controller.MemberController;
import com.fdmgroup.gwbPlatformBulletin.dal.BulletinPostRepository;
import com.fdmgroup.gwbPlatformBulletin.exceptions.NonexistentPostException;
import com.fdmgroup.gwbPlatformBulletin.exceptions.ValidationException;
import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class BulletinPostService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BulletinPostService.class);
	
	@Autowired
    private BulletinPostRepository bulletinRepository;
	private PasswordEncoder encoder;
	private MemberService memberService;
	private MemberController memberController;

	public BulletinPostService(BulletinPostRepository bulletinRepository, PasswordEncoder encoder) {
		super();
		this.bulletinRepository = bulletinRepository;
		this.encoder = encoder;
	}

	/**
	 * Date Published will reflect when post has been submitted and saved to database, not post instantiation
	 * 
	 * @param post
	 * @return bulletin post
	 * @throws ValidationException
	 */
	@Transactional
	public BulletinPost createPost(BulletinPost post) throws ValidationException {
		
		try { 
			validatePost(post);
			System.out.println("NEW POST ADDED:" + post);
			return bulletinRepository.save(post);
			
		} catch (ValidationException ve) {
			throw ve;
			
		} catch (Exception e) {
			throw new RuntimeException("Error saving bulletin post", e);
        }
    }

    private void validatePost(BulletinPost post) throws ValidationException {
    	
    	try {
            if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
                throw new ValidationException("Title cannot be empty");
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
            
        }

        try {
            if (post.getContent() == null || post.getContent().trim().isEmpty()) {
                throw new ValidationException("Content cannot be empty");
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
            
        }
        
        try {
            if (post.getAuthor() == null) {
                throw new ValidationException("Post must have author");
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
            
        }
    }
	
	public List<BulletinPost> getAllPosts() {
        return bulletinRepository.findAll(Sort.by(Sort.Direction.DESC, "datePublished"));
    }
	
	public boolean existsById(int postId) {
        return bulletinRepository.existsById(postId);
    }

    public Optional<BulletinPost> getPostById(int postId) {
        return bulletinRepository.findById(postId);
    }
    
    public List<BulletinPost> findByAuthorId(Integer authorId) {
		return bulletinRepository.findByAuthorId(authorId, Sort.by(Sort.Direction.DESC, "datePublished"));
	}
    
    
    /**
	 * Allows users to make a search of all posts if it matches author name, post title or anything in the post content
	 * Combines JPA
	 * 
	 */
//    public List<BulletinPost> findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String searchTerm, String searchTerm2, String searchTerm3) {
//    	
//    	String searchTermLowercase = "%" + searchTerm.toLowerCase() + "%";
//    	String searchTerm2Lowercase = "%" + searchTerm2.toLowerCase() + "%";
//    	String searchTerm3Lowercase = "%" + searchTerm3.toLowerCase() + "%";
//    	
//    	return bulletinRepository.findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrContentContainingIgnoreCase(searchTermLowercase, searchTerm2Lowercase, searchTerm3Lowercase);
//
//	}


    @Transactional
    public boolean deleteById(Integer postId) {
    	
    	if (bulletinRepository.existsById(postId)) {
    		
    		bulletinRepository.deleteById(postId);
    	
    		return true;
    	
    	}
    	 
    	return false;
    }
    
    @Transactional
	public void saveAll(List<BulletinPost> bulletinBoard) {
		bulletinRepository.saveAll(bulletinBoard);
	}
    
    @Transactional
	public void updatePost(Integer id, @Valid BulletinPost post, Authentication authentication) throws NonexistentPostException, AuthorizationException {
    	    	
    	if (id != post.getId()) {
    		throw new AuthorizationException("Post does not match intended post id");
    	}
    
    	if (bulletinRepository.existsById(id)) {
    				
    		BulletinPost existingPost = bulletinRepository.findById(id)
    				.orElseThrow(() -> new NonexistentPostException("Post with ID " + id + " could not be found"));
    		
    		if (!existingPost.getAuthor().getEmail().equals(authentication.getName())) {
    		    throw new AuthorizationException("You do not have permission to edit this post");
    		}
    		
    		
    		post.setAuthor(existingPost.getAuthor());
    		
    		validatePost(post);
    		
    		post.setDatePublished(existingPost.getDatePublished());
    		post.saveDateEdited();
    		
    		bulletinRepository.save(post);
    		
		} else {
			throw new NonexistentPostException("Post with ID " + id + " could not be found");
			
		}
		
	}

//	public List<BulletinPost> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String searchTerm) {
//		String searchTermLowercase = "%" + searchTerm.toLowerCase() + "%";
//		
//    	return bulletinRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(searchTermLowercase);
//
//	}
	
}
