package com.twitter.poll.entity;

public class UserPollChoice {
	public static String CREATE_NEW_USER_POLL_CHOICE = "insert into user_poll_choice (poll_id, user_id) values (?,?)";
	public static String FIND_BY_USERID_AND_POLLID = "select * from user_poll_choice where poll_id = ? and user_id = ?";
	private int pollId;
	private int userId;

	public UserPollChoice() {
		super();
	}

	public UserPollChoice(int pollId, int userId) {
		super();
		this.pollId = pollId;
		this.userId = userId;
	}

	public int getPollId() {
		return pollId;
	}

	public void setPollId(int pollId) {
		this.pollId = pollId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
