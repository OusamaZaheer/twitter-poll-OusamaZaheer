package com.twitter.poll.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twitter.poll.entity.Poll;

@Repository
public interface PollRepository extends CrudRepository<Poll, Integer> {
	List<Poll> findAll();

	List<Poll> findByLive(boolean live);

	List<Poll> findAllLive();

	List<Poll> findAllNonLive();
}
