package com.fdmgroup.gwbPlatformBulletin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;
import com.fdmgroup.gwbPlatformBulletin.model.Member;
import com.fdmgroup.gwbPlatformBulletin.dal.*;

@Service
public class AuthUserService implements org.springframework.security.core.userdetails.UserDetailsService{
	
	private MemberRepository memberRepository;

	@Autowired
	public AuthUserService(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// CHANGE TO findByName !!!!!!!!!!!
		Member member = this.memberRepository.findByEmail(username).orElseThrow(
				()-> new UsernameNotFoundException(username));
		return new AuthUser(member);
	}

	
	

}
