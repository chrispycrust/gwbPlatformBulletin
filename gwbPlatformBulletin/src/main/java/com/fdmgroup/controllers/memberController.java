package com.fdmgroup.controllers;

@RestController
@RequestMapping("/api")
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
    
    // PUT (update) a member
    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable(value = "id") int memberId, @Valid @RequestBody Member memberDetails) {
        Member updatedMember = memberService.updateMember(memberId, memberDetails);
        if(updatedMember == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedMember);
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