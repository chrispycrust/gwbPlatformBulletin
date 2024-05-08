package com.fdmgroup.gwbPlatformBulletin.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.fdmgroup.gwbPlatformBulletin.model.Member;

public class AuthUser implements org.springframework.security.core.userdetails.UserDetails{
	
	private Member member;

	public AuthUser(Member member) {
		super();
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		System.out.println(new SimpleGrantedAuthority(this.user.getRole().toString()));
//		return Arrays.asList(new SimpleGrantedAuthority(this.user.getRole().toString()));
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return this.member.getPassword();
	}

	@Override
	public String getUsername() {
		return this.member.getEmail();		// change to name getter
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
