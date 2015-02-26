package fr.sid.cae.service;

import fr.sid.cae.domain.Connection;
import fr.sid.cae.domain.exception.PasswordMismatchException;
import fr.sid.cae.domain.exception.UserNotFoundException;

public interface ConnectionService {
	public void verfierConnection(Connection con) throws UserNotFoundException, PasswordMismatchException;
}
