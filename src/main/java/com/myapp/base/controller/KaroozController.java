package com.myapp.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.base.entity.Hero;
import com.myapp.base.model.AuthenticationRequest;
import com.myapp.base.model.AuthenticationResponse;
import com.myapp.base.model.UserModel;
import com.myapp.base.service.JwtService;
import com.myapp.base.service.UserService;

@RestController
public class KaroozController {
	

	@Autowired
	private UserService userService;
	
	@PostMapping("/saveuser")
	public UserModel createUser(@RequestBody UserModel userModel) {		
		return userService.saveUser(userModel);
	}
	
	@GetMapping("/getAllUsers")
	public List<UserModel> getAllUsers() {		
		return userService.getAllUsers();
	}
	
	@GetMapping("/getAllHeros")
	public List<Hero> getAllHeros() {		
		return userService.getAllHeros();
	}
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
