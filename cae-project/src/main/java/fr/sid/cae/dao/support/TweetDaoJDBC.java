package fr.sid.cae.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import fr.sid.cae.dao.TweetDao;
import fr.sid.cae.domain.Tweet;

public class TweetDaoJDBC implements TweetDao {
	
	private static final Logger log = LoggerFactory.getLogger(TweetDao.class);
	
	private static final String GET_TWEETS = "SELECT * FROM tweet WHERE userid = ?";
	private static final String ADD_TWEET  = "INSERT INTO tweet(id, userid, date, content) VALUES(?, ?, ?, ?)";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addTweet(Tweet tweet) {
		jdbcTemplate.update(ADD_TWEET, tweet.getId(), tweet.getUserId(), tweet.getDateDB(), tweet.getContent());	
	}
	
	@Override
	public List<Tweet> getUserTweet(String userId) {
		return jdbcTemplate.query(GET_TWEETS, new TweetMapper(), userId);
	}
	
	private class TweetMapper implements RowMapper<Tweet>{

		@Override
		public Tweet mapRow(ResultSet arg0, int arg1) throws SQLException {
			
			return null;
		}
		
	}
	
}

	
