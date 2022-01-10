package com.twitter.poll.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserPollChoicePK implements Serializable {

	@Column(name = "poll_id", insertable = false, updatable = false)
	private int pollId;

	@Column(name = "user_id", insertable = false, updatable = false)
	private int userId;

	public UserPollChoicePK() {
		super();
	}

	public UserPollChoicePK(int pollId, int userId) {
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
