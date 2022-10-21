package com.example.gatewayApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.gatewayApi.exception.UnAuthorizedUserException;
import com.example.gatewayApi.model.AuthenticationRequest;
import com.example.gatewayApi.model.AuthenticationResponse;
import com.example.gatewayApi.service.MyUserDetailsService;
import com.example.gatewayApi.util.JwtUtil;

@CrossOrigin
@RestController
public class HelloResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	
	@GetMapping({"/hello"})
	public String hello() {
		return ("<h1>Hello World</h1>");
	}
	
	@PostMapping(value="/authenticate")
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest)
					throws UnAuthorizedUserException{
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()
						,authenticationRequest.getPassword()));
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		System.out.println(jwt);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	
	}
	
}
