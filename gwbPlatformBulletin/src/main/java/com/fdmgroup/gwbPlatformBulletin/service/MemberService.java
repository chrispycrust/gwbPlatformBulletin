package com.fdmgroup.gwbPlatformBulletin.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.gwbPlatformBulletin.exceptions.ConflictException;
import com.fdmgroup.gwbPlatformBulletin.exceptions.ValidationException;

import com.fdmgroup.gwbPlatformBulletin.model.Member;
import com.fdmgroup.gwbPlatformBulletin.security.TokenService;
import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;

@Service
public class MemberService {

   private MemberRepository memberRepository;
	private PasswordEncoder encoder;
	private TokenService tokenService;
	
	@Autowired
	public MemberService(MemberRepository memberRepository, PasswordEncoder encoder, TokenService tokenService) {
		super();
		this.memberRepository = memberRepository;
		this.encoder = encoder;
		this.tokenService = tokenService;
	}

	public String registerMember(Member member) throws ConflictException, ValidationException {
		
		validateMember(member);
		
		String email = member.getEmail();
		
		if ( memberRepository.existsByEmail(email) ) {
			throw new ConflictException("Member with name " + email + " already exists");
			
		} else {

			String encodedPassword = encoder.encode(member.getPassword());
			member.setPassword(encodedPassword);

			memberRepository.save(member);
			
			System.out.println("NEW USER ADDED:" + member.getFullName());
			
	        Authentication auth = new UsernamePasswordAuthenticationToken(
	        		
	                member.getEmail(), 
	                member.getPassword(),
	                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	        
	        return tokenService.generateToken(auth);

		}	
    }
	
	private void validateMember(Member member) throws ValidationException {
		
		if (member.getEmail() == null || member.getEmail().trim().isEmpty()) {
	        throw new ValidationException("Email cannot be empty");
	    }
	    if (member.getPassword() == null || member.getPassword().trim().isEmpty()) {
	        throw new ValidationException("Password cannot be empty");
	    }
	    
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Integer memberId) {
    	return memberRepository.findById(memberId);	

    }
    
    public Optional<Member> findMemberByEmail(String email) {
    	return memberRepository.findByEmail(email);	
	}
    
    public List<Member> findBySearch(String searchTerm) {
    	
    	String searchTermLowercase = "%" + searchTerm.toLowerCase() + "%";
    	
    	return memberRepository.findBySearchTerm(searchTermLowercase);

	}

    public void updateMember(Member updatedMember) {
    	
    	validateMember(updatedMember);
    	
    	memberRepository.save(updatedMember);
    	
	}
    
    public void deleteById(Integer memberId) {
    	
    	memberRepository.deleteById(memberId);
    	
    	if (memberRepository.existsById(memberId)) {
			throw new RuntimeException("Delete failed. Member with ID " + memberId + " could not be deleted.");
			
		} else {
			System.out.println("Member with ID " + memberId + " was deleted.");
			
		}
        
    }

	public void saveAll(List<Member> members) { // for committing dummy data
		for (Member member : members ) {
			registerMember(member);
		}
	}
	
}
