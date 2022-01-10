package com.twitter.poll.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.poll.entity.User;
import com.twitter.poll.services.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody User user) throws Exception {
		try {
			return new ResponseEntity<Object>(userService.createOrUpdate(user), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);

		}
	}

//	@GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> login(@RequestParam String userName) throws Exception {
//		try {
//			return new ResponseEntity<Object>(userService.findByUserName(userName), HttpStatus.OK);
//
//		} catch (Exception e) {
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
//
//		}
//	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

}
