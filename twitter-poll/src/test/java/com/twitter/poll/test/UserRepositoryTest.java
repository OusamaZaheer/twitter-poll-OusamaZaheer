package com.twitter.poll.test;

import static org.assertj.core.api.Assertions.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.twitter.poll.entity.User;
import com.twitter.poll.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	private static final String USER_NAME = "ousama";
	private static final String PASSWORD = "ousama";
	private User user;
	@Autowired
	UserService userService;

	@Before
	public void before() throws Exception {
		user = new User(USER_NAME, PASSWORD);
		user = userService.createOrUpdate(user);
		Assert.assertTrue(user.getId() > 0);
	}

	@Test(expected = Exception.class)
	public void createWithSameName() throws Exception {
		User user = new User(USER_NAME, PASSWORD);
		user = userService.createOrUpdate(user);
		fail("User With Name Already Exist");

	}

	@Test
	public void findAll() {
		Assert.assertTrue(userService.findAll().size() > 0);
	}

	@Test
	public void findByUserName() {

		Assert.assertNotNull(userService.findByUserName(USER_NAME));
	}

	@Test
	public void findByUserNameAndPassword() {

		Assert.assertNotNull(userService.findByUserNameAndPassword(USER_NAME, PASSWORD));
	}

	@After
	public void removeUser() {
		userService.remove(user.getId());
	}
}
