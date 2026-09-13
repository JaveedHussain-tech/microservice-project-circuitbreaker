package com.example.auth.util;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	
	public static final String SECRET_KEY="QVNERkdISktMTExMTExT0lVWVRSRVdRQVpYQ1ZCTk1RV0VSVFlV";
	
	public String generateToken(String username) {
		Map<String,Object>claims=new HashMap<>();
			claims.put("email","ayra@gmail.com");
		
		
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*5))
				.claims(claims)
				.signWith(getkey())
				.compact();
		
	}
	
	private SecretKey getkey() { 
		byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(bytes); }
	
	private Claims getClaims(String token) {
		return Jwts.parser().verifyWith(getkey())
				   .build().parseSignedClaims(token)
				   .getPayload();
	}
	
	public Date extractExpiration(String token) {
		
		return getClaims(token).getExpiration();
	}
	
	

}
