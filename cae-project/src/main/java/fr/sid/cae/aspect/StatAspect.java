package fr.sid.cae.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.sid.cae.domain.User;

@Component
@Aspect
public class StatAspect {

	private static final Logger log = LoggerFactory.getLogger(StatAspect.class);
	
	@AfterReturning(pointcut="execution(* fr.sid.cae.service.UserService.authenticate(..))", returning="user")
	public void traceAuthenticate(User user){
		if(log.isDebugEnabled()) {
			log.debug("StatAspect::traceAuthenticate({})", new Object [] {user.getId()});
		}
		
		//code de l'aspect
		log.info("L'utilisateur {} {} s'est correctement authentifié.", new Object [] {user.getFirstName(), user.getName()});		
	}
	
	@AfterReturning(pointcut="execution(* fr.sid.cae.service.UserService.getUserById(..))", returning="user")
	public void traceGetUserById(User user){
		log.debug("StatAspect::traceGetUserById({})", new Object [] {user.getId()});
		
		//code de l'aspect
		log.info("Utilisateur {} {} correctement récupéré.", new Object [] {user.getFirstName(), user.getName()});		
	}
}
