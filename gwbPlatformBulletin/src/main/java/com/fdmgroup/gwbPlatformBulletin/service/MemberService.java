package com.fdmgroup.gwbPlatformBulletin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.gwbPlatformBulletin.exceptions.ConflictException;

import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;
import com.fdmgroup.gwbPlatformBulletin.model.Member;

@Service
public class MemberService {

   private MemberRepository memberRepository;
	private PasswordEncoder encoder;
	
	@Autowired
	public MemberService(MemberRepository memberRepository, PasswordEncoder encoder) {
		super();
		this.memberRepository = memberRepository;
		this.encoder = encoder;
	}

	public void registerMember(Member member) throws ConflictException {
		
		String fullName = member.getFullName().toLowerCase();
		
		if ( memberRepository.existsByFullName(fullName) ) {
			throw new ConflictException("Member with name " + fullName + " already exists");
			
		} else {
			// Password encoding
			String encodedPassword = encoder.encode(member.getPassword());
			member.setPassword(encodedPassword);
			memberRepository.save(member);
			System.out.println("NEW USER ADDED:" + fullName);
			
		}	
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Integer memberId) {
    	return memberRepository.findById(memberId);	

    }
    
    public Optional<Member>findMemberByEmail(String email) {
		
		return null;
	}
    
    public List<Member> findBySearch(String searchTerm) {
    	
    	String searchTermLowercase = "%" + searchTerm.toLowerCase() + "%";
    	
    	return memberRepository.findBySearchTerm(searchTermLowercase);

	}

    public void updateMember(Member updatedMember) {
    
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
		memberRepository.saveAll(members);
		
	}

	
	
}
