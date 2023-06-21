package com.oo2.grupo01.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.oo2.grupo01.entities.UserRole;
import com.oo2.grupo01.services.implementacion.UserService;

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

  @Autowired
  @Qualifier("userService")
  private UserService userService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
    .requestMatchers(HttpMethod.POST, "/login").permitAll()
    .requestMatchers(HttpMethod.GET, "/logout").permitAll()
    .requestMatchers(HttpMethod.POST).hasAuthority(UserRole.ADMIN.name())
    .requestMatchers(HttpMethod.GET).hasAnyAuthority(UserRole.USER.name(),UserRole.ADMIN.name())
    .and()
    .csrf().disable();
    return http.build();
  }

}
