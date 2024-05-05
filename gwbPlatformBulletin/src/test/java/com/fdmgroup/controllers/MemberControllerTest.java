package com.fdmgroup.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fdmgroup.gwbPlatformBulletin.controller.MemberController;
import com.fdmgroup.gwbPlatformBulletin.exceptions.ConflictException;
import com.fdmgroup.gwbPlatformBulletin.model.Member;
import com.fdmgroup.gwbPlatformBulletin.service.MemberService;


@ExtendWith(MockitoExtension.class) 
class MemberControllerTest {

	@Mock
	MemberService mockMemberService;
	
	@InjectMocks // injects mockMemberService
	private MemberController memberController; 
	
	// ----------------------------------------
	// TESTS BEGIN
	// ----------------------------------------
	
	@Test
	void test_registerMember() throws ConflictException {
		
		Member newMember = new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>());
		doNothing().when(mockMemberService).registerMember(any(Member.class));
		
		assertDoesNotThrow(() -> memberController.registerMember(newMember));
		
		verify(mockMemberService).registerMember(newMember);
		
	}
	
	@Test
	void test_registerMember_memberAlreadyExists() throws ConflictException {
		
		// Arrange
		Member currentMember = new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>());
		doThrow(new ConflictException("Member already exists")).when(mockMemberService).registerMember(any(Member.class));

		// Act
        Exception exception = assertThrows(ConflictException.class, () -> {
            memberController.registerMember(currentMember);
        });

        // Assert
        assertTrue(exception.getMessage().contains("Member already exists"));
	}
	
	@Test
	void test_getAllMembers_noMembers() {
		
		// Arrange
		when(mockMemberService.getAllMembers()).thenReturn(Arrays.asList());
		
		// Act
		List<Member> members = memberController.getAllMembers();
		
		// Assert
		assertEquals(0, members.size());
		verify(mockMemberService).getAllMembers();
	}

	@Test
	void test_getAllMembers() {
		
		// Arrange
		Member member1 = new Member("Miss", "Cressida", "Cowper", new ArrayList<>());
        Member member2 = new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>());
        when(mockMemberService.getAllMembers()).thenReturn(Arrays.asList(member1, member2));

        // Act
        List<Member> members = memberController.getAllMembers();

        // Assert
        assertNotNull(members);
        assertEquals(2, members.size());
        assertEquals("Miss Cressida Cowper", members.get(0).getFullName());
        assertEquals("Madame Genevieve Delacroix", members.get(1).getFullName());
        verify(mockMemberService).getAllMembers();
    }
	
	@Test
	void test_getMemberById_found() {
		
		// Arrange
        Integer memberId = 1;
        Member member = new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>());
        when(mockMemberService.getMemberById(memberId)).thenReturn(Optional.of(member));

        // Act
        Member foundMember = memberController.getMemberById(memberId);

        // Assert
        assertNotNull(foundMember);
        verify(mockMemberService).getMemberById(memberId);
		
	}
	
	@Test
	void test_getMemberById_notFound() {
		
		// Assert
		Integer memberId = 34;
		when(mockMemberService.getMemberById(memberId)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
		
		// Act
		Exception exception = assertThrows(ResponseStatusException.class, () -> {
            memberController.getMemberById(memberId);
        });
		
		// Assert
		assertTrue(exception.getMessage().contains("Member not found"));
	}
	
	@Test
	void test_getMemberBySearch_found() {
		
		// Arrange
        String search = "Lady";
		Member member1 = new Member("Lady", "Agatha", "Danbury", new ArrayList<>());
		Member member2 = new Member("Lady", "", "Whistledown", new ArrayList<>());
		Member member3 = new Member("Lady", "Violet", "Bridgerton", new ArrayList<>());
        when(mockMemberService.findBySearch(search)).thenReturn(Arrays.asList(member1, member2, member3));

        // Act
        List<Member> foundMembers = memberController.getMemberBySearch(search);

        // Assert
        assertEquals(3, foundMembers.size());
        verify(mockMemberService).findBySearch(search);
        
	}
	
	@Test
	void test_getMemberBySearch_noResults() {
		
		// Arrange
		String search = "aasds";
        when(mockMemberService.findBySearch(search)).thenReturn(Arrays.asList());
        
        // Act
        List<Member> foundMembers = memberController.getMemberBySearch(search);

        // Assert
        assertEquals(0, foundMembers.size());
        verify(mockMemberService).findBySearch(search);
		
	}
	
	@Test
	void test_updateMember() {
		
		// Arrange
        Integer memberId = 1;
        Member currentMember = new Member("Miss", "Cressida", "Cowper", new ArrayList<>());
        doNothing().when(mockMemberService).updateMember(any(Member.class));
        
        // Act
        memberController.updateMember(memberId, currentMember);

        // Assert
        assertEquals(memberId, currentMember.getId());
        verify(mockMemberService).updateMember(currentMember);
        
		
	}
	
	@Test
	void test_deleteMemberById_successful() {
		
		// Arrange
		Integer memberId = 1;
        doNothing().when(mockMemberService).deleteById(memberId);
       
		// Act
        memberController.deleteMemberById(memberId);
        
		// Assert
        verify(mockMemberService).deleteById(memberId);

	}
	
	@Test
	void test_deleteMemberById_unsuccessful() {
		
		// Arrange
		Integer memberId = 1;
        doThrow(new RuntimeException("Delete failed for member.")).when(mockMemberService).deleteById(memberId);
		
        // Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            memberController.deleteMemberById(1);
        });

        // Assert
        assertTrue(exception.getMessage().contains("Delete failed for member."));
		
	}
	
}
