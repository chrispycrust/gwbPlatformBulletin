package com.fdmgroup.gwbPlatformBulletin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;
import com.fdmgroup.gwbPlatformBulletin.model.Member;

@Service
// this isn't labelled a component?
public class MemberService {
	
	@Autowired
    private MemberRepository memberRepository;

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

    public Member createMember(Member member) {
    	
        return memberRepository.save(member);
        
    }

    public Optional<Member> updateMember(int memberId) {
    	
    	Optional<Member> member = memberRepository.findById(memberId);
    	
    	if(!member.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "member not found");
        } 
    	
//    	member.setHonorific
//    	member.setFirstName(memberDetails.getFirstName());
//        member.setLastName(memberDetails.getLastName());
        
        return memberRepository.save(member);
        
    }

    public boolean deleteMember(int memberId) {
    	
        Optional<Member> member = memberRepository.findById(memberId);
        
        if (member.isPresent()) {
        	
//        	member.getFirstName
        	// if I use an optional to deal with null values, how do I retrieve the first name from the Member class?
        	
            memberRepository.delete(member.get());
            
            return true; // return message instead?
        }
        
        return false;
        
    }

	public void saveAll(List<Member> members) {
		
		for (Member member : members) {
			memberRepository.save(member);
		}
		
	}

}
