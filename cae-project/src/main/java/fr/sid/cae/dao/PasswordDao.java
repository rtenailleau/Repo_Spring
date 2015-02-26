package fr.sid.cae.dao;

import fr.sid.cae.domain.Password;

public interface PasswordDao {

	public String getPassword(String userId);
	
	public void setPassword(Password p);
	
}
