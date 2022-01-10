package com.twitter.poll.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twitter.poll.entity.Poll;
import com.twitter.poll.entity.User;
import com.twitter.poll.entity.UserPollChoice;
import com.twitter.poll.entity.UserPollChoicePK;

@Repository
public interface UserPollChoiceRepository extends CrudRepository<UserPollChoice, UserPollChoicePK> {
	UserPollChoice findByPollAndUser(Poll poll, User user);

}
