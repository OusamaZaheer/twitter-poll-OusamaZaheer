package com.twitter.poll.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
		@NamedQuery(name = "Poll.findAllLive", query = "Select p From Poll p where p.live=true order by p.start ASC"),
		@NamedQuery(name = "Poll.findAllNonLive", query = "Select p From Poll p where p.live=false order by p.end ASC") })
public class Poll implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@Column(name = "start_date")
	private Timestamp start;

	@Column(name = "end_date")
	private Timestamp end;

	@Column(name = "live", columnDefinition = "TINYINT(1) default 0")
	private boolean live;

	@OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PollChoice> pollChoices;

	@OneToMany(mappedBy = "poll", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<UserPollChoice> userPollChoices;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "created_by")
	private User user;

	public Poll(String name, boolean live) {
		super();
		this.name = name;
		this.live = live;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

}
