package fr.sid.cae.service;

import fr.sid.cae.domain.Tweet;
import fr.sid.cae.domain.exception.UserNotFoundException;

public interface TweetService {
	public void addTweet(Tweet tweet) throws UserNotFoundException;
}