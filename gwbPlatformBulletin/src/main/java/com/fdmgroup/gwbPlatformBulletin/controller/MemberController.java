package com.fdmgroup.gwbPlatformBulletin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.gwbPlatformBulletin.model.Member;
import com.fdmgroup.gwbPlatformBulletin.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
    private MemberService memberService;
    
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
    
    @GetMapping("/{id}") // members/{id}
    public Optional<Member> getMemberById(@PathVariable(value = "id") Integer memberId) {
        return memberService.getMemberById(memberId);
    }
    
    @PostMapping("/register")
    public void createMember(@RequestBody Member member) {
        memberService.createMember(member);
    }
    
    @PutMapping("/{id}")
    public Optional<Member> updateMember(@PathVariable(value = "id") Integer memberId) {
    	return memberService.updateMember(memberId);
    }
        
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable(value = "id") Integer memberId) {
    	memberService.deleteMember(memberId);
    }

}