package com.fdmgroup.gwbPlatformBulletin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.gwbPlatformBulletin.exceptions.ConflictException;
import com.fdmgroup.gwbPlatformBulletin.exceptions.ValidationException;
import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;
import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;
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
		
		System.out.println(member);
		
		if ( memberRepository.existsByFullName(fullName) ) {
			throw new ConflictException("Member with name " + fullName + " already exists");
			
		} else {
			// Password encoding
			String encodedPassword = encoder.encode(member.getPassword());
			member.setPassword(encodedPassword);
			
			validateMember(member);
			
			memberRepository.save(member);
			
			System.out.println("NEW USER ADDED:" + fullName);
			
		}	
    }
	
	private void validateMember(Member member) throws ValidationException {
    	
//    	try {
//            if (member.getFirstName().equals("") || member.getFirstName().trim().equals("")) {
//                throw new ValidationException("First name cannot be empty");
//            }
//        } catch (Exception e) {
//            throw new ValidationException(e.getMessage());
//            
//        }
//
//        try {
//            if (member.getLastName().equals("") || member.getLastName().trim().equals("")) {
//                throw new ValidationException("Last Name cannot be empty");
//            }
//        } catch (Exception e) {
//            throw new ValidationException(e.getMessage());
//            
//        }
        
        try {
            if (member.getEmail().equals("") || member.getEmail().trim().equals("")) {
                throw new ValidationException("Email cannot be empty");
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
            
        }
        
        try {
            if (member.getPassword().equals("") || member.getPassword().trim().equals("")) {
                throw new ValidationException("Password cannot be empty");
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
            
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
		// iterate through member
		for (Member member : members ) {
			registerMember(member);
		}
	}
	
}
