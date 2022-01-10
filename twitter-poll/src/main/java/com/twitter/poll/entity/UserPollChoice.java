package com.twitter.poll.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserPollChoice implements Serializable {

	@EmbeddedId
	private UserPollChoicePK id;

	@NotNull
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "poll_id", insertable = false, updatable = false)
	private Poll poll;

	@NotNull
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	public UserPollChoice() {
		super();
	}

	public UserPollChoice(@NotNull Poll poll, @NotNull User user) {
		super();
		this.id = new UserPollChoicePK(poll.getId(), user.getId());
		this.poll = poll;
		this.user = user;
	}

	public UserPollChoicePK getId() {
		return id;
	}

	public void setId(UserPollChoicePK id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

}
