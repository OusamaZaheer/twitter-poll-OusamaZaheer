package com.twitter.poll.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.twitter.poll.entity.User;
import com.twitter.poll.enumration.RoleValue;
import com.twitter.poll.repo.UserRepository;
import com.twitter.poll.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User createOrUpdate(User user) throws Exception {
		User vUser = userRepository.findByUserName(user.getUserName());
		if (vUser != null) {
			throw new Exception("User Already Exist");
		}
		return userRepository.save(user);

	}

	@Override
	public User findByUserName(String contact) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(contact);
		if (user == null) {
			throw new UsernameNotFoundException("User " + contact + " Not Found");
		}

		return user;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Loging In User " + username);
		User user = userRepository.findByUserName(username);
		if (user != null) {
			System.out.println("Founded User" + username);
			return new org.springframework.security.core.userdetails.User(user.getUserName(),
					new BCryptPasswordEncoder().encode(user.getPassword()), buildSimpleGrantedAuthorities());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (RoleValue role : RoleValue.values()) {
			authorities.add(new SimpleGrantedAuthority(role.toString()));
		}
		return authorities;
	}

	@Override
	public User findByUserNameAndPassword(String contact, String password) throws UsernameNotFoundException {
		User user = userRepository.findByUserNameAndPassword(contact, password);
		if (user == null) {
			throw new UsernameNotFoundException("User " + contact + " Not Found");
		}

		return user;
	}

	@Override
	public void remove(int id) {
		userRepository.deleteById(id);

	}

}
