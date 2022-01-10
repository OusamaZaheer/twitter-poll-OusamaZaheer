package com.twitter.poll.repo;

import org.springframework.data.repository.CrudRepository;

import com.twitter.poll.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUserName(String userName);

	User findByUserNameAndPassword(String contact, String password);

}
