package com.twitter.poll.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.twitter.poll.entity.User;

public interface UserService extends UserDetailsService {
	User createOrUpdate(User user) throws Exception;

	User findByUserName(String contact) throws UsernameNotFoundException;

	User findByUserNameAndPassword(String contact, String password) throws UsernameNotFoundException;

	List<User> findAll();
}
