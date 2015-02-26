package fr.sid.cae.domain;

public class Activation {

	private String userId;
	private String activationKey;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivationKey() {
		return activationKey;
	}
	
	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
	
}
