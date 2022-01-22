package com.twitter.poll.services;

import java.util.List;

import com.twitter.poll.entity.Poll;

public interface PollService {
	Poll create(Poll poll) throws Exception;

	List<Poll> findAll();

	List<Poll> findByLive(boolean live);

	Poll findById(int id);

	Poll addVote(int userId, int choiceId) throws Exception;

	List<Poll> findAllLive();

	List<Poll> findAllNonLive();

	Poll endPoll(int id);
}
