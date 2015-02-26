package fr.sid.cae.web.servlet;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import fr.sid.cae.web.mvc.config.MvcConfig;

public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {

		// Setup DispatcherTypes
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
				
		// Setup the UTF-8 Filter
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
			characterEncodingFilter.setEncoding("UTF-8");
		FilterRegistration.Dynamic characterEncodingRegistration = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
			characterEncodingRegistration.addMappingForServletNames(dispatcherTypes, true, "mvcServlet");
					
		// Setup the main Spring Application Context through ContextLoaderListener
		/*AnnotationConfigWebApplicationContext mainContext = new AnnotationConfigWebApplicationContext();
			mainContext.register(SesameDaoConfig.class);
			servletContext.addListener(new ContextLoaderListener(mainContext));*/
				
		// Setup the MVC Servlet Context through DispatcherServlet
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
			mvcContext.register(MvcConfig.class);
		ServletRegistration.Dynamic mvcDispatcher = servletContext.addServlet("mvcServlet", new DispatcherServlet(mvcContext));
			mvcDispatcher.addMapping("*.html");
	}

}
