package fr.sid.cae.service.support;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.sid.cae.dao.ActivationDao;
import fr.sid.cae.dao.PasswordDao;
import fr.sid.cae.dao.UserDao;
import fr.sid.cae.domain.Activation;
import fr.sid.cae.domain.Password;
import fr.sid.cae.domain.User;
import fr.sid.cae.domain.exception.ActivationNotFoundException;
import fr.sid.cae.domain.exception.AuthenticationException;
import fr.sid.cae.domain.exception.UserExistException;
import fr.sid.cae.domain.exception.UserNotFoundException;
import fr.sid.cae.service.UserService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceDao implements UserService {

	private UserDao userDao;
	private ActivationDao activationDao;
	private PasswordDao passwordDao;
	private SecureRandom rand = new SecureRandom();
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setActivationDao(ActivationDao activationDao) {
		this.activationDao = activationDao;
	}

	@Autowired
	public void setPasswordDao(PasswordDao passwordDao) {
		this.passwordDao = passwordDao;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, 
			isolation = Isolation.SERIALIZABLE, 
			rollbackFor = {RuntimeException.class, UserExistException.class})
	public void registerAccount(User u) throws UserExistException {
		
		// Set User UUID
		u.setId(UUID.randomUUID().toString());
		
		// Create User entry
		try {
			userDao.addUser(u);
		}
		catch(DuplicateKeyException e) {
			throw new UserExistException("User exists " + u.getMail());
		}
		
		// Create Activation entry
		Activation a = new Activation();
			a.setActivationKey(UUID.randomUUID().toString());
			a.setUserId(u.getId());
		
		// Send mail
		// TODO
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, 
			isolation = Isolation.SERIALIZABLE)
	public User activateAccount(String activationKey) throws ActivationNotFoundException {
		
		// Get Activation entry
		try {
			return userDao.getUser(activationDao.getActivation(activationKey));
			
			/*User u = userDao.getUser(activationDao.getActivation(activationKey));
					
			u.setValid(true);
			userDao.updateUser(u);
			
			activationDao.deleteActivation(activationKey);
			
			return u;*/
		}
		catch(IncorrectResultSizeDataAccessException e) {
			throw new ActivationNotFoundException("Activation not found " + activationKey);
		}
	}

	public void changePassword(Password password) {
		
		// Cipher Password
		// TODO
		
		// Set Password
		passwordDao.setPassword(password);	
	}

	public User authenticate(String mail, String password) throws AuthenticationException {
		
		// Cipher Password
		// TODO
		
		// Get User entry
		try {
			User u = userDao.getUserByMail(mail);
			if(!passwordDao.getPassword(u.getId()).equals(password)) {
				throw new AuthenticationException("Authentication failed " + mail);
			}
			
			return u;
		}
		catch(IncorrectResultSizeDataAccessException e) {
			throw new AuthenticationException("Authentication failed " + mail);
		}
	}

	public User getUserById(String userId) throws UserNotFoundException {
		try {
			return userDao.getUser(userId);
		}
		catch(IncorrectResultSizeDataAccessException e) {
			throw new UserNotFoundException("User not found " + userId);
		}
	}

	public User getUserByMail(String mail) throws UserNotFoundException {
		try {
			return userDao.getUserByMail(mail);
		}
		catch(IncorrectResultSizeDataAccessException e) {
			throw new UserNotFoundException("User not found " + mail);
		}
	}

	public void unregisterAccount(User u) {
		// TODO
	}

	@Override
	public void addUser(User user) throws UserExistException {
		// TODO Auto-generated method stub
		user.setId(UUID.randomUUID().toString());
		userDao.addUser(user);
		
		// Password généré aléatoirement
		Password p = new Password();
		String newMdp = new BigInteger(130, rand).toString();
		
		p.setUserId(user.getId());
		p.setPassword(newMdp);
		p.setConfirmPassword(newMdp);

		passwordDao.setPassword(p);
		
		//Génération de l'activation
		Activation a = new Activation();
		a.setActivationKey(new BigInteger(130, rand).toString());
		a.setUserId(user.getId());
		
		activationDao.addActivation(a);
		
	}
	
}
