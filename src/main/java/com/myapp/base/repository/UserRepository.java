package com.myapp.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.myapp.base.entity.User;

@Component
public interface UserRepository extends JpaRepository<User,Long> {
	
	public List<User> findByUserName(String userName);
}
