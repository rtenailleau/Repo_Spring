package fr.sid.cae.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.sid.cae.domain.Connection;
import fr.sid.cae.service.UserService;
import fr.sid.cae.web.validation.support.ConnectionValidator;

@Controller
public class ConnectionController {
	
	private UserService userService;
	private ConnectionValidator connectionValidator;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setConnectionValidator(ConnectionValidator connectionValidator) {
		this.connectionValidator = connectionValidator;
	}
	
	@ModelAttribute("connectUser")
    public Connection getConnection() {
        return new Connection();
    }
     
    @RequestMapping(value = "/connection.html", method = RequestMethod.GET)
    public String showConnection() {
        return "connexion";
    }
     
    @RequestMapping(value = "/connection.html", method = RequestMethod.POST)
    public String addTweet(@ModelAttribute("connect") Connection con, BindingResult result, RedirectAttributes redirectAttributes) {
         
        // Validation
        connectionValidator.validate(con, result);
        if(result.hasErrors())
            return "connection";
         
        // Ajout de l'utilisateur
        userService.getUserByMail(con.getMail());
        return "add-tweet-success";
    }
}