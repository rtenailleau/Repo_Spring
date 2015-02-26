package fr.sid.cae.service.support;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.sid.cae.dao.TweetDao;
import fr.sid.cae.dao.UserDao;
import fr.sid.cae.domain.Tweet;
import fr.sid.cae.domain.User;
import fr.sid.cae.domain.exception.UserExistException;
import fr.sid.cae.domain.exception.UserNotFoundException;
import fr.sid.cae.service.TweetService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TweetServiceDao implements TweetService{

	private TweetDao tweetDao;
	private UserDao userDao;
	
	@Autowired
	public void setTweetDao(TweetDao tweetDao) {
		this.tweetDao = tweetDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, 
	isolation = Isolation.SERIALIZABLE, 
	rollbackFor = {RuntimeException.class, UserExistException.class})
	public void addTweet(Tweet tweet) {
		// on insère le tweet, on connait déjà l'iduser et le content		
		try{
			//on vérifie que le user existe
			User u = userDao.getUser(tweet.getUserId());
			
			tweet.setId(UUID.randomUUID().toString());
			tweet.setDate(new Date());
			
			tweetDao.addTweet(tweet);
		} catch(UserNotFoundException e) {
			throw e;
		}
	}

}
