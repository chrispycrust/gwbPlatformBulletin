package com.fdmgroup.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.fdmgroup.gwbPlatformBulletin.controller.BulletinPostController;
import com.fdmgroup.gwbPlatformBulletin.exceptions.NonexistentPostException;
import com.fdmgroup.gwbPlatformBulletin.exceptions.ValidationException;
import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;
import com.fdmgroup.gwbPlatformBulletin.service.BulletinPostService;

@ExtendWith(MockitoExtension.class) 
public class BulletinPostControllerTest {
	
	@Mock 
	BulletinPostService bulletinService;
	
	@InjectMocks
	BulletinPostController bulletinController;
	
	@Test
    void test_createPost() {
        
		BulletinPost post = new BulletinPost();
		when(bulletinService.createPost(post)).thenReturn(post);
        
		assertEquals(post, bulletinController.createPost(post));
    }
	
	@Test
    void test_createPost_fail() {
        
		BulletinPost post = new BulletinPost();
        ValidationException validationException = new ValidationException("Validation failed");
        when(bulletinService.createPost(post)).thenThrow(validationException);
        
        assertThrows(ResponseStatusException.class, () -> bulletinController.createPost(post));
    }
    
    @Test
    void testGetAllPosts() {
    	
        List<BulletinPost> posts = new ArrayList<>();
        when(bulletinService.getAllPosts()).thenReturn(posts);
        
        assertEquals(posts, bulletinController.getAllPosts());
    }
    
    @Test
    void testGetPostById() {
    	
        BulletinPost post = new BulletinPost();
        when(bulletinService.getPostById(1)).thenReturn(Optional.of(post));
        
        assertEquals(post, bulletinController.getPostById(1));
    }
    
    @Test
    void test_GetPostById_notFound() {
    	
    	Integer postId = 2;
        when(bulletinService.getPostById(postId)).thenReturn(Optional.empty());
        
        assertThrows(ResponseStatusException.class, () -> bulletinController.getPostById(postId));
    }
    
    
    @Test
    void testUpdatePost() {
    	
    	Integer postId = 1;
        BulletinPost post = new BulletinPost();

        assertDoesNotThrow(() -> bulletinController.updatePost(postId, post));
    }
    
    @Test
    void testUpdatePost_unsuccessful() throws NonexistentPostException {
    	
    	Integer postId = 1;
        BulletinPost post = new BulletinPost();
        
        doThrow(ValidationException.class).when(bulletinService).updatePost(postId, post);
        
        assertThrows(ResponseStatusException.class, () -> bulletinController.updatePost(postId, post));
    }
    
    @Test
    void testDeletePost_successful() {
    	
    	Integer postId = 1;
        when(bulletinService.deleteById(postId)).thenReturn(true);
        
        assertDoesNotThrow(() -> bulletinController.deletePost(postId));
    }
    
    @Test
    void testDeletePost_unsuccessful() {
    	
    	Integer postId = 1;
    	when(bulletinService.deleteById(postId)).thenReturn(false);
        
        assertThrows(ResponseStatusException.class, () -> bulletinController.deletePost(postId));
    }
}
