package com.fdmgroup.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.gwbPlatformBulletin.dal.BulletinPostRepository;
import com.fdmgroup.gwbPlatformBulletin.exceptions.NonexistentPostException;
import com.fdmgroup.gwbPlatformBulletin.exceptions.ValidationException;
import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;
import com.fdmgroup.gwbPlatformBulletin.service.BulletinPostService;

@ExtendWith(MockitoExtension.class) 
public class BulletinPostServiceTest {
	
	@Mock
    private BulletinPostRepository mockBulletinRepository;

	@InjectMocks
    private BulletinPostService bulletinService;
	
	@Test
	void test_CreatePost_Success() throws ValidationException {
		
	    BulletinPost post = new BulletinPost();
	    post.setTitle("Valid Title");
	    post.setContent("Valid Content");
	    when(mockBulletinRepository.save(any(BulletinPost.class))).thenReturn(post);

	    BulletinPost savedPost = bulletinService.createPost(post);
	    
	    assertNotNull(savedPost);
	    verify(mockBulletinRepository).save(post);
	}

	@Test
	void test_CreatePost_ValidationException() {
	    BulletinPost post = new BulletinPost();

	    assertThrows(ValidationException.class, () -> {
	        bulletinService.createPost(post);
	    });
	}
	
	@Test
	void test_GetAllPosts() {
		
	    List<BulletinPost> expectedPosts = Arrays.asList(new BulletinPost(), new BulletinPost());
	    when(mockBulletinRepository.findAll()).thenReturn(expectedPosts);

	    List<BulletinPost> actualPosts = bulletinService.getAllPosts();
	    
	    assertEquals(expectedPosts.size(), actualPosts.size());
	    verify(mockBulletinRepository).findAll();
	}
	
	
	@Test
	void test_GetPostById_Found() {
		
	    Optional<BulletinPost> expectedPost = Optional.of(new BulletinPost());
	    when(mockBulletinRepository.findById(anyInt())).thenReturn(expectedPost);

	    Optional<BulletinPost> actualPost = bulletinService.getPostById(1);
	    
	    assertTrue(actualPost.isPresent());
	    verify(mockBulletinRepository).findById(1);
	}

	@Test
	void test_GetPostById_NotFound() {
	    when(mockBulletinRepository.findById(anyInt())).thenReturn(Optional.empty());

	    Optional<BulletinPost> actualPost = bulletinService.getPostById(1);
	    
	    assertFalse(actualPost.isPresent());
	    verify(mockBulletinRepository).findById(1);
	}
	
	@Test
	void test_UpdatePost_Success() throws NonexistentPostException {
		
	    BulletinPost post = new BulletinPost();
	    post.setId(1);
	    when(mockBulletinRepository.existsById(anyInt())).thenReturn(true);
	    when(mockBulletinRepository.save(any(BulletinPost.class))).thenReturn(post);

	    bulletinService.updatePost(1, post);
	    
	    verify(mockBulletinRepository).save(post);
	}

	@Test
	void test_UpdatePost_NonexistentPostException() {
		
	    BulletinPost post = new BulletinPost();
	    post.setId(1);

	    when(mockBulletinRepository.existsById(anyInt())).thenReturn(false);

	    assertThrows(NonexistentPostException.class, () -> {
	        bulletinService.updatePost(1, post);
	    });
	}
	
	@Test
	void test_DeleteById_Success() {
	    when(mockBulletinRepository.existsById(anyInt())).thenReturn(true);
	    doNothing().when(mockBulletinRepository).deleteById(anyInt());

	    assertTrue(bulletinService.deleteById(1));
	    verify(mockBulletinRepository).deleteById(1);
	}

	@Test
	void test_DeleteById_Failure() {
	    when(mockBulletinRepository.existsById(anyInt())).thenReturn(false);

	    assertFalse(bulletinService.deleteById(1));
	    verify(mockBulletinRepository, never()).deleteById(anyInt());
	}
	
	@Test
	void test_SearchPosts() {
	    List<BulletinPost> expectedResults = Arrays.asList(new BulletinPost());
	    when(mockBulletinRepository.findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrContentContainingIgnoreCase(anyString())).thenReturn(expectedResults);

	    List<BulletinPost> results = bulletinService.findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrContentContainingIgnoreCase("test");
	    
	    assertEquals(1, results.size());
	    verify(mockBulletinRepository).findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrContentContainingIgnoreCase("%test%");
	}
	
}
