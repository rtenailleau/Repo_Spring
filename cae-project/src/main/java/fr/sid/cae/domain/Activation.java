package fr.sid.cae.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activation")
public class Activation {

	@Id
	@Column(name = "id", length = 36)
	private String userId;
	@Column(name = "activationkey", unique = true)
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
