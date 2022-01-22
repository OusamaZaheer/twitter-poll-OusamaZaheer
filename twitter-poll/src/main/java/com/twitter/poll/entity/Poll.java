package com.twitter.poll.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

public class Poll implements Serializable {
	public static String CREATE_NEW_POLL = "insert into poll (name, start_date, live, created_by) values (?,?,?,?)";
	public static String FIND_BY_ID = "select * from poll where id =?";
	public static String FIND_ALL = "select * from poll";
	public static String FIND_BY_LIVE_STATUS = "select * from poll where live = ?";
	public static String FIND_5_LIVE = "select * from poll where live = true order by start_date asc limit 5";
	public static String FIND_5_OFFLINE = "select * from poll where live = false order by end_date asc limit 5";
	public static String END_POLL = "UPDATE  poll  SET end_date = ? , live = ?  WHERE id = ? ";

	private int id;

	private String name;

	private Timestamp start;

	private Timestamp end;

	private boolean live;

	private Set<PollChoice> pollChoices;

	private Set<UserPollChoice> userPollChoices;

	private int userId;

	public Poll(String name, boolean live) {
		super();
		this.name = name;
		this.live = live;
	}

	public Poll(int id, String name, Timestamp start, Timestamp end, boolean live, int userId) {
		super();
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.live = live;
		this.userId = userId;
	}

	public Poll() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public Set<PollChoice> getPollChoices() {
		if (this.pollChoices == null) {
			this.pollChoices = new LinkedHashSet<PollChoice>();
		}
		return pollChoices;
	}

	public void setPollChoices(Set<PollChoice> pollChoices) {
		this.pollChoices = pollChoices;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public Set<UserPollChoice> getUserPollChoices() {
		if (this.userPollChoices == null) {
			this.userPollChoices = new LinkedHashSet<UserPollChoice>();
		}
		return userPollChoices;
	}

	public void setUserPollChoices(Set<UserPollChoice> userPollChoices) {
		this.userPollChoices = userPollChoices;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
