package com.oo2.grupo01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();
	  }
	
	 @Bean
	    public PasswordEncoder securityPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}