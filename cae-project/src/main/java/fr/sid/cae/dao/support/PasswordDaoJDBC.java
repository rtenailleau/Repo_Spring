package fr.sid.cae.dao.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.sid.cae.dao.PasswordDao;
import fr.sid.cae.domain.Password;

@Repository
public class PasswordDaoJDBC implements PasswordDao {

	private static final String GET_PASSWORD = "SELECT password FROM password WHERE id = ?";
	private static final String SET_PASSWORD = "INSERT INTO password(id, password, confirmPassword) VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE confirmPassword = ?";
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getPassword(String userId) {
		return jdbcTemplate.queryForObject(GET_PASSWORD, String.class, userId);
	}
	
	public void setPassword(Password p) {
		jdbcTemplate.update(SET_PASSWORD, p.getUserId(), p.getPassword(), p.getConfirmPassword(),p.getConfirmPassword());
	}

}
