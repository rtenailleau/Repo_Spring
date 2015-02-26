package fr.sid.cae.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(value = {"fr.sid.cae.aspect"})
@EnableAspectJAutoProxy
public class AspectConfig {

}
