package fr.sid.cae.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	@ModelAttribute("name")
	public String getName(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		return name;
	}
	
	@RequestMapping("/index.html")
	public String showIndex() {
		return "hello";
//		return "add-user";
	}
	
	@RequestMapping("/authentification.html")
	public String showConnection() {
		return "connection";
	}
	
	
	
}
