package fr.sid.cae.dao.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.sid.cae.dao.ActivationDao;
import fr.sid.cae.domain.Activation;

@Repository
public class ActivationDaoJDBC implements ActivationDao {

	private static final String GET_ACTIVATION = "SELECT userid FROM activation WHERE id = ?";
	private static final String ADD_ACTIVATION = "INSERT INTO activation(id, activationkey) VALUES(?, ?)";
	private static final String DEL_ACTIVATION = "DELETE FROM activation WHERE id = ?";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getActivation(String activationId) {
		return jdbcTemplate.queryForObject(GET_ACTIVATION, String.class, activationId);
	}

	public void addActivation(Activation a) {
		jdbcTemplate.update(ADD_ACTIVATION, a.getActivationKey(), a.getUserId());
	}

	public void deleteActivation(String activationId) {
		jdbcTemplate.update(DEL_ACTIVATION, activationId);
	}

}
