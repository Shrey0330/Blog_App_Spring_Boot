package com.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.JwtAuthRequest;
import com.blog.app.security.JwtAuthResponse;
import com.blog.app.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
	@Autowired
	private JwtTokenHelper jwttokenhelp;
@Autowired
private UserDetailsService udetailserv;
@Autowired
private AuthenticationManager aumanger;
	@PostMapping("/login")
public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
	this.authenticate(request.getEmail(),request.getPassword());
UserDetails uDetails=	this.udetailserv.loadUserByUsername(request.getEmail());
	String token=this.jwttokenhelp.generateToken(uDetails);
	JwtAuthResponse response=new JwtAuthResponse();
	response.setToken(token);
	
	return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
}
	private void authenticate(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken aToken=new UsernamePasswordAuthenticationToken(email, password);
		
		try {
			this.aumanger.authenticate(aToken);
			
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			System.out.println("Invalid Username and password");
		throw new Exception("Invalid Username and password");
		}	
		}
}
