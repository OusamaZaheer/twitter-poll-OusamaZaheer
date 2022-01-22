package com.twitter.poll.restcontroller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.poll.entity.Poll;
import com.twitter.poll.services.PollService;

@RestController
@RequestMapping(value = "/poll")
public class PollController {

	@Autowired
	private PollService pollService;

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody Poll poll) throws Exception {
		try {
			poll.setStart(Timestamp.valueOf(LocalDateTime.now()));
			return new ResponseEntity<Object>(pollService.create(poll), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);

		}
	}

	@PostMapping(value = "/end", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> end(@RequestParam int pollId) throws Exception {
		try {
			return new ResponseEntity<Object>(pollService.endPoll(pollId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);

		}
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Poll>> getAll() {
		return new ResponseEntity<List<Poll>>(pollService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/get-all-by-status", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Poll>> getAllByStatus(@RequestParam boolean live) {
		if (live) {
			return new ResponseEntity<List<Poll>>(pollService.findAllLive(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Poll>>(pollService.findAllNonLive(), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/cast-vote", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> castVote(@RequestParam int userId, @RequestParam int choiceId) {
		try {
			return new ResponseEntity<Object>(pollService.addVote(userId, choiceId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);

		}
	}

}
