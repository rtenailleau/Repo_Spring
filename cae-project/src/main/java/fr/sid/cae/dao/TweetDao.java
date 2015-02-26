package fr.sid.cae.dao;

import java.util.List;

import fr.sid.cae.domain.Tweet;

public interface TweetDao {
	public List<Tweet> getUserTweet(String userId);
}
