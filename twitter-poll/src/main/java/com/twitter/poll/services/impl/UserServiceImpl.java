package com.twitter.poll.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twitter.poll.entity.User;
import com.twitter.poll.enumration.RoleValue;
import com.twitter.poll.mapper.ObjectMapper;
import com.twitter.poll.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public UserServiceImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User createOrUpdate(User user) throws Exception {
		User vUser = findByUserName(user.getUserName());
		if (vUser != null) {
			throw new Exception("User Already Exist");
		}
		int result = jdbcTemplate.update(User.CREATE_NEW_USER, user.getUserName(), user.getPassword());
		return result == 1 ? findByUserName(user.getUserName()) : null;
	}

	@Override
	public User findByUserName(String contact) {
		User user = null;
		List<User> users = jdbcTemplate.query(User.FIND_BY_USER_NAME, ObjectMapper.getInstance().new UserMapper(),
				contact);
		if (!users.isEmpty()) {
			user = users.get(0);
		}

		return user;
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query(User.FIND_ALL, ObjectMapper.getInstance().new UserMapper());
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Loging In User " + username);
		User user = findByUserName(username);
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
		User user = null;
		List<User> users = jdbcTemplate.query(User.FIND_BY_USER_NAME_AND_PASSWORD,
				ObjectMapper.getInstance().new UserMapper(), contact, password);
		if (!users.isEmpty()) {
			user = users.get(0);
		}
		if (user == null) {
			throw new UsernameNotFoundException("User " + contact + " Not Founded With Password");
		}

		return user;
	}

	@Override
	public void remove(int id) {
		jdbcTemplate.update(User.DELETE, id);
	}

}
