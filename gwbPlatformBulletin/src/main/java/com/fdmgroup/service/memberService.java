package com.fdmgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class memberService {
	
	@Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(int memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(int memberId, Member memberDetails) {
    	
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            member.setFirstName(memberDetails.getFirstName());
            member.setLastName(memberDetails.getLastName());
            return memberRepository.save(member);
        }
        return null;
    }

    public boolean deleteMember(int memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isPresent()) {
            memberRepository.delete(member.get());
            return true;
        }
        return false;
    }
}
