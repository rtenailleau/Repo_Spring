package fr.sid.cae.web.validation.support;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import fr.sid.cae.domain.Connection;
import fr.sid.cae.domain.User;
import fr.sid.cae.web.validation.Validator;

@Component
public class ConnectionValidator implements Validator<Connection> {

	@Override
	public void validate(Connection t, Errors errors) {
		validateMail(t.getMail(), errors);
		validatePassword(t.getPassword(), errors);		
	}

	private void validatePassword(String password, Errors errors) {
		if(password == null || "".compareTo(password)==0) {
			errors.rejectValue("password", "connection.password.empty", "Le mot de passe ne peut être vide.");
		}
	}

	private void validateMail(String mail, Errors errors) {
        if(mail == null || "".equals(mail)) {
            errors.rejectValue("mail", "connection.mail.empty", "Vous devez saisir une adresse");
        }
        else if(!mail.matches("[-_.a-zA-Z0-9]+@[-_.a-zA-Z0-9]+")) {
            errors.rejectValue("mail", "connection.mail.invalid", "L'adresse est invalide");
        }
    }
}