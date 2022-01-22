package com.twitter.poll.entity;

public class PollChoice {
	public static String CREATE_NEW_POLL_CHOICE = "insert into poll_choice (description, poll_id) values (?,?)";
	public static String FIND_BY_ID = "select * from poll_choice where id = ?";
	public static String CAST_VOTE = "UPDATE  poll_choice  SET vote = vote + 1 WHERE id = ? ";
	public static String FIND_BY_POLL_ID = "select * from poll_choice where poll_id = ?";

	private int id;

	private String description;

	private long vote;

	private int pollId;

	public PollChoice() {
		super();
	}

	public PollChoice(int id, String description, long vote, int pollId) {
		super();
		this.id = id;
		this.description = description;
		this.vote = vote;
		this.pollId = pollId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getVote() {
		return vote;
	}

	public void setVote(long vote) {
		this.vote = vote;
	}

	public int getPollId() {
		return pollId;
	}

	public void setPollId(int pollId) {
		this.pollId = pollId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PollChoice other = (PollChoice) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}
