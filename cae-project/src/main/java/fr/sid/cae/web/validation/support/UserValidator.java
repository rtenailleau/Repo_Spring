package fr.sid.cae.web.validation.support;
 
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import fr.sid.cae.domain.User;
import fr.sid.cae.web.validation.Validator;
 
@Component
public class UserValidator implements Validator<User> {
 
    public void validate(User u, Errors errors) {
        validateName(u.getName(), errors);
        validateFirstName(u.getFirstName(), errors);
        validateMail(u.getMail(), errors);
    }
     
    private void validateName(String name, Errors errors) {
        if(name == null || "".equals(name)) {
            errors.rejectValue("name", "user.name.empty", "Vous devez saisir un nom");
        }
    }
     
    private void validateFirstName(String firstName, Errors errors) {
        if(firstName == null || "".equals(firstName)) {
            errors.rejectValue("firstName", "user.firstName.empty", "Vous devez saisir un prénom");
        }
    }
     
    private void validateMail(String mail, Errors errors) {
        if(mail == null || "".equals(mail)) {
            errors.rejectValue("mail", "user.mail.empty", "Vous devez saisir une adresse");
        }
        else if(!mail.matches("[-_.a-zA-Z0-9]+@[-_.a-zA-Z0-9]+")) {
            errors.rejectValue("mail", "user.mail.invalid", "L'adresse est invalide");
        }
    }
}