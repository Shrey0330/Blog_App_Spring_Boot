package com.blog.app.security;

import java.util.Date;

import java.util.HashMap;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.blog.app.config.AppConstants;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenHelper {
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
private String secret="jwtTokenKey";
public String getUserName(String token) {
	return getClaimFromToken(token,Claims::getSubject);
}

public Date getExpiratinDateFromToken(String token)
{
	return getClaimFromToken(token, Claims::getExpiration);
}
public <T> T getClaimFromToken(String token,Function<Claims, T> claimsResolver)

{
	final Claims claims=getAllClaimsFromToken(token);
	
return claimsResolver.apply(claims);	
}

public Claims getAllClaimsFromToken(String token) {
	// TODO Auto-generated method stub
	return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
}
public Boolean isTokenExpired(String token)
{
	final Date expiration=getExpiratinDateFromToken(token);
	return expiration.before(new Date());
}
public String generateToken(UserDetails userdeatails)
{
	HashMap<String, Object> claimsMap=new HashMap<>();
	return doGenerateToken(claimsMap,userdeatails.getUsername());
	
}

private String doGenerateToken(HashMap<String, Object> claimsMap, String username) {
	// TODO Auto-generated method stub
	return Jwts.builder().setClaims(claimsMap)
			.setSubject(username)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*100))
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
}
public Boolean validateToken(String token,UserDetails userDetails)
{
	final String userName=getUserName(token);
	return (userName.equals(userDetails.getUsername())&&!isTokenExpired(token));
}
}
