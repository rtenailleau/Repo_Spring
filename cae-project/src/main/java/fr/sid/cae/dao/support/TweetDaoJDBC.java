package fr.sid.cae.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import fr.sid.cae.dao.TweetDao;
import fr.sid.cae.domain.Tweet;

public class TweetDaoJDBC implements TweetDao {
	
	@Override
	public List<Tweet> getUserTweet(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class TweetMapper implements RowMapper<Tweet>{

		@Override
		public Tweet mapRow(ResultSet arg0, int arg1) throws SQLException {
			
			return null;
		}
		
	}
	
}

	
