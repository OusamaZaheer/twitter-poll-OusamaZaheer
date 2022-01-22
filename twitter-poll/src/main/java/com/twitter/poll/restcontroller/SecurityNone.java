package com.twitter.poll.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.poll.entity.User;
import com.twitter.poll.services.UserService;

@RestController
@RequestMapping(value = "/public")
public class SecurityNone {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestParam String userName, @RequestParam String password) {
		User user = null;
		try {
			user = userService.findByUserNameAndPassword(userName, password);
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		} catch (UsernameNotFoundException e) {
			try {
				user = userService.createOrUpdate(new User(userName, password));
				return new ResponseEntity<Object>(user, HttpStatus.OK);
			} catch (Exception e1) {
				return new ResponseEntity<Object>(e1.getMessage(), HttpStatus.EXPECTATION_FAILED);
			}
		}
	}

//	@GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> login(@RequestParam String userName) throws Exception {
//		try {
//			return new ResponseEntity<Object>(userService.loadUserByUsername(userName), HttpStatus.OK);
////			return new ResponseEntity<Object>(userService.findByuserName(userName),HttpStatus.OK);
//
//		} catch (Exception e) {
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
//
//		}
//	}
}
