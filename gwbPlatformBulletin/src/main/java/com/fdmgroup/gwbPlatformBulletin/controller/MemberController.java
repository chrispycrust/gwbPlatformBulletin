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

import com.fdmgroup.gwbPlatformBulletin.exceptions.ConflictException;
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
    
    @GetMapping("/{search}")
    public List<Member> getMemberByName(@PathVariable(value = "search") String search) {
        return memberService.findBySearch(search);
    }
    
    
    @PostMapping("/register")
    public void registerMember(@RequestBody Member member) throws ConflictException {
        memberService.registerMember(member);
    }
    
    @PutMapping() // not sure what endpoint should be, you'd update member on the member profile page?
    public void updateMember(Member member) {
    	memberService.updateMember(member);
    }
        
    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable(value = "id") Integer memberId) {
    	memberService.deleteById(memberId);
    }

}