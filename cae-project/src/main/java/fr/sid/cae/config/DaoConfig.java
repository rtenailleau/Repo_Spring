package fr.sid.cae.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan(value = {"fr.sid.cae.dao.support"})
@EnableCaching(mode = AdviceMode.PROXY)
@PropertySource(value = {"classpath:config.properties", "file:C:/toto.properties"}, ignoreResourceNotFound = true)
public class DaoConfig {
	@Value("${db.driver") String dbDriver;
	@Value("${db.url") String dbUrl;
	@Value("${db.user") String dbUser;
	@Value("${db.pass") String dbPassword;
	
	@Value("${db.mailhost") String dbMailhost;
	@Value("${db.mailport") int dbMailport;
	@Value("${db.mailprotocol") String dbMailprotocol;
	@Value("${db.mailusername") String dbMailusername;
	@Value("${db.mailpassword") String dbMailpassword;
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
			jdbcTemplate.setDataSource(dataSource(dbDriver, dbUrl, dbUser, dbPassword));
		return jdbcTemplate;
	}
	
	@Bean
	public DataSource dataSource(String dbDriver, String dbUrl, String dbUser, String dbPassword) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(dbDriver);
			dataSource.setUrl(dbUrl);
			dataSource.setUsername(dbUser);
			dataSource.setPassword(dbPassword);
		return dataSource;
	}
	
	@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager("users");
	}
	
	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(dbMailhost);
	    mailSender.setPort(dbMailport);
	    mailSender.setProtocol(dbMailprotocol);
	    mailSender.setUsername(dbMailusername);
	    mailSender.setPassword(dbMailpassword);
	    mailSender.setJavaMailProperties(mailSenderProperties());
	    return mailSender;
	}
	 
	private Properties mailSenderProperties() {
		Properties mailSenderProperties = new Properties();
	    mailSenderProperties.setProperty("mail.smtp.auth", "true");
	    mailSenderProperties.setProperty("mail.smtp.starttls.enable", "true");
	    mailSenderProperties.setProperty("mail.smtp.ssl.trust", "smtp.univ-lorraine.fr");
	    return mailSenderProperties;
	}
	
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
}
