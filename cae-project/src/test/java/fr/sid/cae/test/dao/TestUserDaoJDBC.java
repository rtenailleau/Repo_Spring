package fr.sid.cae.test.dao;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.sid.cae.config.DaoConfig;
import fr.sid.cae.dao.UserDao;
import fr.sid.cae.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DaoConfig.class})
public class TestUserDaoJDBC {
	
	@Autowired
	@Qualifier("userdao-jdbc")
	UserDao userDao;
	
	@Test
	public void testUserService() {
		
		User user = new User();
			user.setId(UUID.randomUUID().toString());
			user.setMail("mathieu.larchet@univ-lorraine.fr");
			user.setName("LARCHET");
			user.setFirstName("Mathieu");
			
			userDao.addUser(user);
		
		Assert.assertNotNull(userDao.getUser(user.getId()));
		
		userDao.deleteUser(user.getId());
	}

}
