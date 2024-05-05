package com.fdmgroup.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;
import com.fdmgroup.gwbPlatformBulletin.exceptions.ConflictException;
import com.fdmgroup.gwbPlatformBulletin.model.Member;
import com.fdmgroup.gwbPlatformBulletin.service.MemberService;

@ExtendWith(MockitoExtension.class) 
class MemberServiceTest {
	
	@Mock
	private MemberRepository memberRepository;
	
	@InjectMocks
	private MemberService memberService;
	
	@Test
	void test_registerMember_successful() {
		
		Member newMember = new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>());
		newMember.setId(1);
		
		when(memberRepository.existsByFullName(anyString())).thenReturn(false);
	    
		assertDoesNotThrow(() -> memberService.registerMember(newMember));
	    verify(memberRepository).save(newMember);
		
	}
	
	@Test
	void test_registerMember_unsuccessful() {
		
		Member member = new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>());
		member.setId(1);
		
		when(memberRepository.existsByFullName("Madame Genevieve Delacroix")).thenReturn(true);
		
	    assertThrows(ConflictException.class, () -> memberService.registerMember(member));
		
	}
	
	@Test
	void test_getAllMembers_noMembers() {
		
		when(memberRepository.findAll()).thenReturn(new ArrayList<>());
		
	    assertTrue(memberService.getAllMembers().isEmpty());
	}
	
	@Test
	void test_getAllMembers() {
		
		Member member = new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>());
		member.setId(1);
		
		List<Member> members = new ArrayList<>(List.of(member));
		
	    when(memberRepository.findAll()).thenReturn(members);
	    
	    assertFalse(memberService.getAllMembers().isEmpty());
		
	}
	
	@Test
	void test_getMembersById_successful() {
		
		Member member = new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>());
		member.setId(1);
		
		when(memberRepository.findById(1)).thenReturn(Optional.of(member));
	    
		assertEquals(member, memberService.getMemberById(1).orElse(null));
		
	}
	
	@Test
	void test_getMembersById_unsuccessful() {
		
		Member member = new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>());
		member.setId(1);
		
		when(memberRepository.findById(1)).thenReturn(Optional.empty());
	    
		assertFalse(memberService.getMemberById(1).isPresent());
		
	}
	
	@Test
	void test_findBySearch_returnResults() {
		
		String searchTerm = "lady";
		Member member1 = new Member("Lady", "Agatha", "Danbury", new ArrayList<>());
		Member member2 = new Member("Lady", "", "Whistledown", new ArrayList<>());
		Member member3 = new Member("Lady", "Violet", "Bridgerton", new ArrayList<>());
		
		when(memberRepository.findBySearchTerm(searchTerm)).thenReturn(Arrays.asList(member1, member2, member3));
	    
		assertFalse(memberService.findBySearch(searchTerm).isEmpty());
		
	}
	
	@Test
	void test_findBySearch_returnNoResults() {
		
		String searchTerm = "asdasd";
		when(memberRepository.findBySearchTerm(searchTerm)).thenReturn(new ArrayList<>());
	    
		assertTrue(memberService.findBySearch(searchTerm).isEmpty());
		
	}
	
	@Test
	void test_updateMember_successful() {
		
		Member member = new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>());
		member.setId(1);
		
		assertDoesNotThrow(() -> memberService.updateMember(member));
		
	    verify(memberRepository).save(member);
		
	}
	
	@Test
	void test_updateMember_unsuccessful() {
		
	}
	
	@Test
	void test_deleteById_successful() {
		
		Integer memberId = 1;
		doNothing().when(memberRepository).deleteById(memberId);
		
	    assertDoesNotThrow(() -> memberService.deleteById(memberId));
		
	}
	
	@Test
	void test_deleteById_unsuccessful() {
		
		Integer memberId = 1;
		doThrow(new RuntimeException("Delete failed")).when(memberRepository).deleteById(memberId);
	    
		assertThrows(RuntimeException.class, () -> memberService.deleteById(1));
		
	}
	
	@Test
	void test_saveAll() {
		
		Member member1 = new Member("Lady", "Agatha", "Danbury", new ArrayList<>());
		Member member2 = new Member("Lady", "", "Whistledown", new ArrayList<>());
		Member member3 = new Member("Lady", "Violet", "Bridgerton", new ArrayList<>());
		
		List<Member> members = new ArrayList<>(Arrays.asList(member1, member2, member3));
	    
		assertDoesNotThrow(() -> memberService.saveAll(members));
	    verify(memberRepository, times(members.size())).save(any(Member.class));
		
	}

}
