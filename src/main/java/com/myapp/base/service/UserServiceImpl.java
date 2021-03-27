package com.myapp.base.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.base.entity.Hero;
import com.myapp.base.entity.User;
import com.myapp.base.model.UserModel;
import com.myapp.base.repository.UserRepository;
 
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	 @Autowired
	 private ModelMapper modelMapper;	

	@Override
	public UserModel saveUser(UserModel userModel) {
		User user = userRepo.save(convertToUser(userModel));
		userModel.setId(user.getId());
		return userModel;
	}

	@Override
	public UserModel getUser(UserModel userModel) {
		return convertToUserModel(userRepo.getOne(userModel.getId()));
	}

	@Override
	public List<UserModel> getAllUsers() {
		return userRepo.findAll().stream().map(this::convertToUserModel).collect(Collectors.toList());
	}
	
	private UserModel convertToUserModel(User user) {
		UserModel userModel = modelMapper.map(user, UserModel.class);	    
	    return userModel;
	}
	
	private User convertToUser(UserModel userModel) {
		User user = modelMapper.map(userModel, User.class);	    
	    return user;
	}
	
	private Hero convertToHeros(User user) {
		Hero hero = modelMapper.map(user, Hero.class);	
		hero.setName(user.getUserName());
	    return hero;
	}

	@Override
	public List<Hero> getAllHeros() {
		return userRepo.findAll().stream().map(this::convertToHeros).collect(Collectors.toList());
	}

	@Override
	public List<UserModel> getUserByName(String username) {
		return userRepo.findByUserName(username).stream().map(this::convertToUserModel).collect(Collectors.toList());
	}

}
