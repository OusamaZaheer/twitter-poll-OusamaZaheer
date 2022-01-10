package com.twitter.poll.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.twitter.poll.entity.PollChoice;

@Repository
public interface PollChoiceRepository extends CrudRepository<PollChoice, Integer> {

}
