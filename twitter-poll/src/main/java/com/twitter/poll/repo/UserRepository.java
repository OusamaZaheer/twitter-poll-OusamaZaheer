package com.twitter.poll.repo;

import org.springframework.data.repository.CrudRepository;

import com.twitter.poll.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUserName(String password);

	User findByUserNameAndPassword(String contact, String password);

}
