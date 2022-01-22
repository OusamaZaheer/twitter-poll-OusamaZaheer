package com.twitter.poll.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import com.twitter.poll.entity.Poll;
import com.twitter.poll.entity.PollChoice;
import com.twitter.poll.entity.UserPollChoice;
import com.twitter.poll.mapper.ObjectMapper;
import com.twitter.poll.services.PollService;

@Service
public class PollServiceImpl implements PollService {

//	@PersistenceContext
//	private EntityManager em;

//	private PollRepository pollRepository;
//	private UserRepository userRepository;
//	private UserPollChoiceRepository userPollChoiceRepository;
//	private PollChoiceRepository pollChoiceRepository;

//	@Autowired
//	publicc PollServiceImpl(PollRepository pollRepository, UserRepository userRepository,
//			UserPollChoiceRepository userPollChoiceRepository, PollChoiceRepository pollChoiceRepository) {
//		super();
//		this.pollRepository = pollRepository;
//		this.userRepository = userRepository;
//		this.userPollChoiceRepository = userPollChoiceRepository;
//		this.pollChoiceRepository = pollChoiceRepository;
//	}

	@Autowired
	private JdbcTemplate jdbcTamplate;

	@Override
	public Poll create(Poll poll) throws Exception {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		int result = jdbcTamplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preparedStatement = con.prepareStatement(Poll.CREATE_NEW_POLL,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, poll.getName());
				preparedStatement.setTimestamp(2, poll.getStart());
				preparedStatement.setBoolean(3, poll.isLive());
				preparedStatement.setInt(4, poll.getUserId());
				return preparedStatement;
			}
		}, holder);
		if (result == 1) {
			int pollPrimaryKey = holder.getKey().intValue();
			for (PollChoice pollChoice : poll.getPollChoices()) {
				jdbcTamplate.update(PollChoice.CREATE_NEW_POLL_CHOICE, pollChoice.getDescription(), pollPrimaryKey);
			}
			Poll vPoll = findById(pollPrimaryKey);
			vPoll.getPollChoices().addAll(jdbcTamplate.query(PollChoice.FIND_BY_POLL_ID,
					ObjectMapper.getInstance().new PollChoiceMapper(), poll.getId()));
			return findById(pollPrimaryKey);

		}
		return null;
	}

	@Override
	public List<Poll> findAll() {
		return jdbcTamplate.query(Poll.FIND_ALL, ObjectMapper.getInstance().new PollMapper());
	}

	@Override
	public Poll addVote(int userId, int choiceId) throws Exception {
		PollChoice pollChoice = findPollChoiceById(choiceId);
		if (pollChoice != null) {
			UserPollChoice userPollChoice = findUserPollChoice(pollChoice.getPollId(), userId);
			if (userPollChoice == null) {
				int result = jdbcTamplate.update(PollChoice.CAST_VOTE, choiceId);
				if (result == 1) {
					jdbcTamplate.update(UserPollChoice.CREATE_NEW_USER_POLL_CHOICE, pollChoice.getPollId(), userId);
					Poll poll = findById(pollChoice.getPollId());
					poll.getPollChoices().addAll(jdbcTamplate.query(PollChoice.FIND_BY_POLL_ID,
							ObjectMapper.getInstance().new PollChoiceMapper(), poll.getId()));
					return poll;
				}
			} else {
				throw new Exception("You Already Voted For This Poll");
			}

		}
		return null;
	}

	@Override
	public List<Poll> findByLive(boolean live) {
		return addPollChoices(
				jdbcTamplate.query(Poll.FIND_BY_LIVE_STATUS, ObjectMapper.getInstance().new PollMapper(), live));
	}

	@Override
	public Poll findById(int id) {

		return jdbcTamplate.queryForObject(Poll.FIND_BY_ID, ObjectMapper.getInstance().new PollMapper(), id);
	}

	@Override
	public Poll endPoll(int id) {
		int result = jdbcTamplate.update(Poll.END_POLL, Timestamp.valueOf(LocalDateTime.now()), false, id);
		return result == 1 ? findById(id) : null;
	}

	private PollChoice findPollChoiceById(int id) {
		return jdbcTamplate.queryForObject(PollChoice.FIND_BY_ID, ObjectMapper.getInstance().new PollChoiceMapper(),
				id);
	}

	private UserPollChoice findUserPollChoice(int poll_id, int user_id) {
		try {
			return jdbcTamplate.queryForObject(UserPollChoice.FIND_BY_USERID_AND_POLLID,
					ObjectMapper.getInstance().new UserPollChoiceMapper(), poll_id, user_id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Poll> findAllLive() {
		return addPollChoices(jdbcTamplate.query(Poll.FIND_5_LIVE, ObjectMapper.getInstance().new PollMapper()));
	}

	private List<Poll> addPollChoices(List<Poll> polls) {
		for (Poll poll : polls) {
			poll.getPollChoices().addAll(jdbcTamplate.query(PollChoice.FIND_BY_POLL_ID,
					ObjectMapper.getInstance().new PollChoiceMapper(), poll.getId()));
		}
		return polls;
	}

	@Override
	public List<Poll> findAllNonLive() {
		return addPollChoices(jdbcTamplate.query(Poll.FIND_5_OFFLINE, ObjectMapper.getInstance().new PollMapper()));
	}

}
