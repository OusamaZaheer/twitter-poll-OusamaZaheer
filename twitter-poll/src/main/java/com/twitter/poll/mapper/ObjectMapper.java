package com.twitter.poll.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.twitter.poll.entity.Poll;
import com.twitter.poll.entity.PollChoice;
import com.twitter.poll.entity.User;
import com.twitter.poll.entity.UserPollChoice;

public class ObjectMapper {

	private static ObjectMapper objectMapper;

	public static ObjectMapper getInstance() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper;

	}

	public class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"));
		}
	}

	public class PollMapper implements RowMapper<Poll> {

		public Poll mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Poll(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("start_date"),
					rs.getTimestamp("end_date"), rs.getBoolean("live"), rs.getInt("created_by"));
		}
	}

	public class PollChoiceMapper implements RowMapper<PollChoice> {

		public PollChoice mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new PollChoice(rs.getInt("id"), rs.getString("description"), rs.getLong("vote"),
					rs.getInt("poll_id"));
		}
	}

	public class UserPollChoiceMapper implements RowMapper<UserPollChoice> {

		public UserPollChoice mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new UserPollChoice(rs.getInt("poll_id"), rs.getInt("user_id"));
		}
	}
}
