package com.fdmgroup.gwbPlatformBulletin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.gwbPlatformBulletin.dal.BulletinPostRepository;
import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;
import com.fdmgroup.gwbPlatformBulletin.exceptions.NonexistentPostException;
import com.fdmgroup.gwbPlatformBulletin.exceptions.ValidationException;
import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;
import com.fdmgroup.gwbPlatformBulletin.model.Member;

import jakarta.transaction.Transactional;

@Service
public class BulletinPostService {
	
	@Autowired
    private BulletinPostRepository bulletinRepository;
	private PasswordEncoder encoder;

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
			post.setDatePublished(LocalDateTime.now());
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
            throw new ValidationException("Cannot process title: " + e.getMessage());
            
        }

        try {
            if (post.getContent() == null || post.getContent().trim().isEmpty()) {
                throw new ValidationException("Content cannot be empty");
            }
        } catch (Exception e) {
            throw new ValidationException("Cannot process content: " + e.getMessage());
            
        }
    }
	
	public List<BulletinPost> getAllPosts() {
        return bulletinRepository.findAll();
    }

    public Optional<BulletinPost> getPostById(int postId) {
        return bulletinRepository.findById(postId);
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
    
//    public List<BulletinPost> findPostsByAuthorId(Integer authorId) {
//        return bulletinRepository.findByAuthorId(authorId);
//    }

    @Transactional
    public void updatePost(Integer id, BulletinPost post) throws NonexistentPostException {
    	
    	post.setId(id);
    	
    	if (bulletinRepository.existsById(id)) {
    		bulletinRepository.save(post);
			
		} else {
			throw new NonexistentPostException("Post with ID " + id + " could not be found");
			
		}

    }
    
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

	public List<BulletinPost> getPostsByAuthorName(String authorName) {
		return bulletinRepository.findPostByAuthor(authorName);
	}

	
	
}
