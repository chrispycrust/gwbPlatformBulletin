package com.fdmgroup.gwbPlatformBulletin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
import com.fdmgroup.gwbPlatformBulletin.security.AuthUser;
import com.fdmgroup.gwbPlatformBulletin.service.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
    private MemberService memberService;
	
	@PostMapping("/register")
    public String registerMember(@Valid @RequestBody Member member) {
	
		try {
	        String token = memberService.registerMember(member);
	        return token;
	        
	    } catch (ConflictException ex) {
	        throw new ResponseStatusException(HttpStatus.CONFLICT, "Member already exists in database");
	    } 
       
    }
//	
//	@GetMapping ("/me")
//	public Member getCurrentlyLoggedInUser(Authentication auth) {
//
//		return memberService.findMemberByEmail(auth.getName())
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
//	}
	
//	@GetMapping("/me")
//	public Member getCurrentlyLoggedInUser(Authentication authentication) {
//		
//		
//	    AuthUser currentUser = (AuthUser) authentication.getPrincipal();
//	    Integer userId = currentUser.getUserId();  // Get user ID from AuthUser
//
//	    return memberService.getMemberById(userId)
//	        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
//	}
	
	@GetMapping("/me")
    public ResponseEntity<String> getCurrentlyLoggedInUser(@AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Extract standard claims
        String userId = jwt.getSubject(); // 'sub' claim, typically the user ID
        String email = jwt.getClaimAsString("email"); // Custom claim, ensure your JWTs include it if needed

        // Optionally, extract custom claims
        String role = jwt.getClaimAsString("role");
        
        // Use extracted information
        return ResponseEntity.ok("User ID: " + userId + ", Email: " + email + ", Role: " + role);
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
    	member.setId(id); // matches the id specified at the endpoint, ensures we're updating the right entity
    	memberService.updateMember(member);
    }
    
//    @PutMapping("/{id}/updateEmail")
//    public ResponseEntity<String> updateEmail(@PathVariable Integer id, @RequestParam String newEmail) {
//        Member existingMember = memberService.findMemberById(id)
//            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with id: " + id));
//
//        if (!newEmail.equals(existingMember.getEmail())) {
//            memberService.updateEmail(id, newEmail);
//            return ResponseEntity.ok("Email update requested. Please verify the new email to complete the update.");
//        } else {
//            return ResponseEntity.badRequest().body("New email is the same as the old email.");
//        }
//    }


    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable(value = "id") Integer memberId) {
    	memberService.deleteById(memberId);
    }
    
    @GetMapping("/whoami")
    public String whoAmI(Authentication authentication) {
        return "Current user ID: " + authentication.getName();
    }

}