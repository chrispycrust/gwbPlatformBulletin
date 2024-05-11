 package com.fdmgroup.gwbPlatformBulletin.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.stereotype.Service;

import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;

@Service
public class TokenService {

    private final JwtEncoder encoder;
    private final MemberRepository memberRepository;
    private final JwtDecoder decoder;
    
    public TokenService(JwtEncoder encoder, JwtDecoder decoder, MemberRepository memberRepository) {
        this.encoder = encoder;
        this.decoder = decoder;
		this.memberRepository = memberRepository;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        
        String userId;
        if (authentication.getPrincipal() instanceof AuthUser) {
            AuthUser authUser = (AuthUser) authentication.getPrincipal();
            userId = String.valueOf(authUser.getUserId());
        } else {
            throw new IllegalStateException("Expected AuthUser as the principal");
        }

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(userId)  // Set the user ID as the subject
                .claim("email", authentication.getName())
                .claim("scope", scope)
                .build();
        
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    
    public Optional<String> getsubject(String token) {
    	try {
    		Jwt jwt = decoder.decode(token);
    	    return Optional.of(jwt.getSubject());

    	} catch (JwtValidationException e) {
    		return null;
    	}
    }
    

}