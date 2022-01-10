package com.twitter.poll.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.poll.entity.Poll;
import com.twitter.poll.entity.PollChoice;
import com.twitter.poll.entity.User;
import com.twitter.poll.entity.UserPollChoice;
import com.twitter.poll.repo.PollChoiceRepository;
import com.twitter.poll.repo.PollRepository;
import com.twitter.poll.repo.UserPollChoiceRepository;
import com.twitter.poll.repo.UserRepository;
import com.twitter.poll.services.PollService;

@Service
public class PollServiceImpl implements PollService {

	@PersistenceContext
	private EntityManager em;

	private PollRepository pollRepository;
	private UserRepository userRepository;
	private UserPollChoiceRepository userPollChoiceRepository;
	private PollChoiceRepository pollChoiceRepository;

	@Autowired
	public PollServiceImpl(PollRepository pollRepository, UserRepository userRepository,
			UserPollChoiceRepository userPollChoiceRepository, PollChoiceRepository pollChoiceRepository) {
		super();
		this.pollRepository = pollRepository;
		this.userRepository = userRepository;
		this.userPollChoiceRepository = userPollChoiceRepository;
		this.pollChoiceRepository = pollChoiceRepository;
	}

	@Override
	public Poll createOrUpdate(Poll poll) throws Exception {
		return pollRepository.save(poll);
	}

	@Override
	public List<Poll> findAll() {
		return pollRepository.findAll();
	}

	@Override
	public Poll addVote(int userId, int choiceId) throws Exception {
		User vUser = userRepository.findById(userId).get();
		PollChoice pollChoice = pollChoiceRepository.findById(choiceId).get();
		Poll poll = pollChoice.getPoll();
		if (userPollChoiceRepository.findByPollAndUser(poll, vUser) == null) {
			pollChoice.setVote(pollChoice.getVote() + 1);
			pollChoiceRepository.save(pollChoice);
			poll.getUserPollChoices().add(new UserPollChoice(poll, vUser));
		} else {
			throw new Exception("You Already Voted For This Poll");
		}
		return createOrUpdate(poll);
	}

	@Override
	public List<Poll> findByLive(boolean live) {
		return pollRepository.findByLive(live);
	}

	@Override
	public Poll findById(int id) {
		return pollRepository.findById(id).get();
	}

	@Override
	public List<Poll> findAllLive() {
		return em.createNamedQuery("Poll.findAllLive").setMaxResults(5).getResultList();
	}

	@Override
	public List<Poll> findAllNonLive() {
		return em.createNamedQuery("Poll.findAllNonLive").setMaxResults(5).getResultList();
	}

}
