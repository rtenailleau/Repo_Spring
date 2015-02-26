package fr.sid.cae.web.validation.support;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import fr.sid.cae.domain.Tweet;
import fr.sid.cae.web.validation.Validator;

@Component
public class TweetValidator implements Validator<Tweet>{

	@Override
	public void validate(Tweet t, Errors errors) {
		validateContent(t.getContent(), errors);		
	}
	
	private void validateContent(String content, Errors errors){
		if(content == null || "".compareTo(content)==0) {
            errors.rejectValue("content", "tweet.content.empty", "Vous ne pouvez pas envoyer de message vide.");
        }
	}

}