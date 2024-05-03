package com.fdmgroup.gwbPlatformBulletin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.gwbPlatformBulletin.dal.BulletinPostRepository;
import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;

@Service
public class BulletinPostService {
	
	@Autowired
    private BulletinPostRepository bulletinRepo;

    public List<BulletinPost> getAllPosts() {
        return bulletinRepo.findAll();
    }

    public Optional<BulletinPost> getPostById(int postId) {
        return Optional.of(bulletinRepo.findById(postId).orElse(null));
    }
    
    // get post by author name
    
    // get post by author id
    
    // get post by honorific

    public BulletinPost createPost(BulletinPost post) {
        return bulletinRepo.save(post);
    }

    public BulletinPost updatePost(int postId, BulletinPost postDetails) {
    	
    	BulletinPost post = bulletinRepo.findById(postId).orElse(null);
        
        if (post != null) {
        	
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            return bulletinRepo.save(post);
            
        }
        
        return post;
    }

    public boolean deletePost(int postId) {
    	
        Optional<BulletinPost> post = bulletinRepo.findById(postId);
        
        if (post.isPresent()) {
        	
        	bulletinRepo.delete(post.get());
            return true;
            
        }
        
        return false;
    }

	public void saveAll(List<BulletinPost> bulletinBoard) {
		
		for ( BulletinPost post : bulletinBoard ) {
			bulletinRepo.save(post);
		}
		
	}

}
