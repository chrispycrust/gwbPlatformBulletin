package com.fdmgroup.gwbPlatformBulletin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdmgroup.gwbPlatformBulletin.exceptions.ConflictException;
import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;
import com.fdmgroup.gwbPlatformBulletin.model.Member;

@Service
// this isn't labelled a component?
public class MemberService {
	
	@Autowired
    private MemberRepository memberRepository;
	
	// how is this method used in conjunction with controller and user input from form?
	public void registerMember(Member member) throws ConflictException {
		
		if ( memberRepository.existsByFullName(member.getFullName())) {
			throw new ConflictException("Member with name " + member.getFullName() + " already exists");
			
		} else {
			memberRepository.save(member);
			System.out.println("Member added: " + member.getFullName());
			
		}		
        
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(int memberId) {
    	
    	Optional<Member> member = memberRepository.findById(memberId);
        
        if(!member.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "member not found");
        } 
        
        return member;
    }
    
    public List<Member> findBySearch(String search) {
		return memberRepository.findByPartialMatch(search);
		
	}

//    public Member updateMember(Integer memberId) {
//    	
//    	Optional<Member> member = memberRepository.findById(memberId);
//    	
//    	if(!member.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "member not found");
//        } 
//    	
////    	member.setHonorific
////    	member.setFirstName(memberDetails.getFirstName());
////        member.setLastName(memberDetails.getLastName());
//        
//        return memberRepository.save(member);
//        
//    }
    
    // how is this method used in with controller and user input from form?
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

	public void saveAll(List<Member> members) {
		
		for (Member member : members) {
			memberRepository.save(member);
		}
		
	}
	
}
