package fr.sid.cae.dao;

import fr.sid.cae.domain.Activation;

public interface ActivationDao {

	public String getActivation(String activationId);
	
	public void addActivation(Activation a);
	
	public void deleteActivation(String activationId);
	
}
