package fr.sid.cae.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.sid.cae.domain.Tweet;
import fr.sid.cae.service.TweetService;
import fr.sid.cae.web.validation.support.TweetValidator;

@Controller
public class AddTweetController {
	
	private TweetService tweetService;
    private TweetValidator tweetValidator;
    
    @Autowired
	public void setTweetService(TweetService tweetService) {
		this.tweetService = tweetService;
	}
    
    @Autowired
	public void setTweetValidator(TweetValidator tweetValidator) {
		this.tweetValidator = tweetValidator;
	}

	@ModelAttribute("addmessage")
    public Tweet getAddTweet() {
        return new Tweet();
    }
     
    @RequestMapping(value = "/add-message.html", method = RequestMethod.GET)
    public String showAddUser() {
        return "add-message";
    }
     
    @RequestMapping(value = "/add-message.html", method = RequestMethod.POST)
    public String addTweet(@ModelAttribute("addtweet") Tweet tweet, BindingResult result, RedirectAttributes redirectAttributes) {
         
        // Validation
        tweetValidator.validate(tweet, result);
        if(result.hasErrors())
            return "add-message";
         
        // Ajout de l'utilisateur
        tweetService.addTweet(tweet);
        return "add-tweet-success";
    }
}
