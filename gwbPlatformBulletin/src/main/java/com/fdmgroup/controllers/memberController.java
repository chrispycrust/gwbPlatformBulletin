package com.fdmgroup.controllers;

// how can i use class from another package?

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/community")
public class memberController {
	
	@Autowired
    private MemberService memberService;
    
    // GET all members
    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
    
    // GET a member by ID
    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable(value = "id") int memberId) {
        Member member = memberService.getMemberById(memberId);
        if(member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(member);
    }
    
    // POST a new member
    @PostMapping("/members")
    public Member createMember(@Valid @RequestBody Member member) {
    	
        return memberService.createMember(member);
        
    }
    
    // PUT (update) a member's first name & lastname ... if by firstName, multiple members can have same name
    // use Optional
    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable(value = "id") int memberId) {
    	
        Member updatedMember = memberService.updateMember(memberId, memberDetails);
        
        Optional<Member> member = memberService.getMemberById(memberId);
        
        if(!member.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "member not found");
        }
        
        // update member
        
        // save member to database and return?? or will this be tested by Postman?
        
    }
        
    
    // DELETE a member
    @DeleteMapping("/members/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable(value = "id") int memberId) {
    	
        if(memberService.deleteMember(memberId)) {
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }

}