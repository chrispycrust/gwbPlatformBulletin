package com.fdmgroup.gwbPlatformBulletin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.gwbPlatformBulletin.dal.BulletinPostRepository;
import com.fdmgroup.gwbPlatformBulletin.exceptions.ValidationException;
import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;

@Service
public class BulletinPostService {
	
	@Autowired
    private BulletinPostRepository bulletinRepository;
	
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
            throw new ValidationException("Error processing title: " + e.getMessage());
            
        }

        try {
            if (post.getContent() == null || post.getContent().trim().isEmpty()) {
                throw new ValidationException("Content cannot be empty");
            }
        } catch (Exception e) {
            throw new ValidationException("Error processing content: " + e.getMessage());
            
        }
    }
	
	public List<BulletinPost> getAllPosts() {
        return bulletinRepository.findAll();
    }

    public Optional<BulletinPost> getPostById(int postId) {
        return Optional.of(bulletinRepository.findById(postId).orElse(null));
    }
    
    public Optional<List<BulletinPost>> getPostByMemberName(String memberName) {
		// TODO Auto-generated method stub
		return null;
	}
    
    public Optional<List<BulletinPost>> getPostByMemberId(Integer memberId) {
		// TODO Auto-generated method stub
		return null;
	}
    
    // get post by honorific

    public void updatePost(BulletinPost post) {
    	bulletinRepository.save(post);

    }

    public void deleteById(Integer postId) {
    	
    	bulletinRepository.deleteById(postId);
    	
    	if (bulletinRepository.existsById(postId)) {
			throw new RuntimeException("Delete failed. Post with ID " + postId + " could not be deleted.");
			
		} else {
			System.out.println("Post with ID " + postId + " was deleted.");
			
		}
    }

	public void saveAll(List<BulletinPost> bulletinBoard) {
		
		for ( BulletinPost post : bulletinBoard ) {
			bulletinRepository.save(post);
		}
	}

	
	
}
