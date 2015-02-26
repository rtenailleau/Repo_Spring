package fr.sid.cae.service;

import fr.sid.cae.domain.Password;
import fr.sid.cae.domain.User;
import fr.sid.cae.domain.exception.ActivationNotFoundException;
import fr.sid.cae.domain.exception.AuthenticationException;
import fr.sid.cae.domain.exception.UserExistException;
import fr.sid.cae.domain.exception.UserNotFoundException;

public interface UserService {

	public void registerAccount(User u) throws UserExistException;
	
	public User activateAccount(String activationKey) throws ActivationNotFoundException;
	
	public void changePassword(Password password);
	
	public User authenticate(String mail, String password) throws AuthenticationException;
	
	public User getUserById(String userId) throws UserNotFoundException;
	
	public User getUserByMail(String mail) throws UserNotFoundException;
	
	public void unregisterAccount(User u);

	public void addUser(User user) throws UserExistException;
	
}
