package com.fdmgroup.gwbPlatformBulletin.controller;

import java.util.List;

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

import com.fdmgroup.gwbPlatformBulletin.exceptions.ConflictException;
import com.fdmgroup.gwbPlatformBulletin.model.Member;
import com.fdmgroup.gwbPlatformBulletin.service.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
    private MemberService memberService;
	
	@PostMapping("/register")
    public void registerMember(@Valid @RequestBody Member member) throws ConflictException {
		System.out.println(member);
        memberService.registerMember(member);
    }
    
	@GetMapping ("/me")
	public Member getCurrentlyLoggedInUser(Authentication auth) {
		return memberService.findMemberByEmail(auth.getName())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
	}
	
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
    
    /**
     * To help confirm Spring Security is recognising member email as username 
     * i.e. can we retrieve member by email?
     * 
     * @param email
     * @return member object
     */
    @GetMapping ("/email/{email}")
	public Member getEmail(@PathVariable(value = "email") String email) {
		return memberService.findMemberByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
	}
    
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable(value = "id") Integer memberId) {
        return memberService.getMemberById(memberId)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with ID" + memberId));
    }
    
    @GetMapping("/search/{searchTerm}")
    public List<Member> getMemberBySearch(@PathVariable(value = "searchTerm") String searchTerm) {
        return memberService.findBySearch(searchTerm);
    }

    @PutMapping("/{id}")
    public void updateMember(@PathVariable Integer id, @Valid @RequestBody Member member) {
    	System.out.println("Member object details:" + member);
    	member.setId(id); // matches the id specified at the endpoint, ensures we're updating the right entity
    	memberService.updateMember(member);
    }
        
    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable(value = "id") Integer memberId) {
    	memberService.deleteById(memberId);
    }

}