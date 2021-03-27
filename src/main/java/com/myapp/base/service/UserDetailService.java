package com.myapp.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myapp.base.model.UserModel;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		List<UserModel> userDetails = userService.getUserByName(username);
		if (userDetails != null && userDetails.size() > 0) {
			return new User(userDetails.get(0).getUserName(), userDetails.get(0).getPassword(), new ArrayList<>());
		}
		return null;
	}

}
