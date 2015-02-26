package fr.sid.cae.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import fr.sid.cae.domain.User;
import fr.sid.cae.domain.ScopedValue;
import fr.sid.cae.domain.ScopedObject;

@Configuration
@ComponentScan({"fr.sid.cae.web.mvc", "fr.sid.cae.web.validation.support"})
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class);
			viewResolver.setContentType("UTF-8");
			viewResolver.setPrefix("/WEB-INF/jsp/");
			viewResolver.setSuffix(".jspx");
		return viewResolver;
	}

	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
	@Qualifier("current-user")
	
	public ScopedValue<User> currentUser() {
		return new ScopedObject<User>();
	}
}
