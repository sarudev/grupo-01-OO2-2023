package com.oo2.grupo01.services.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.User;
import com.oo2.grupo01.repositories.ISensorRepository;
import com.oo2.grupo01.repositories.IUserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;




@Service("userService")
public class UserService {

	 private final IUserRepository userRepository;
	    private final BCryptPasswordEncoder passwordEncoder;

	    public UserService(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }

	    public boolean isPasswordCorrect(User user, String password) {
	        return passwordEncoder.matches(password, user.getPassword());
	    }

	    public String generateToken(User user) {
	        Claims claims = Jwts.claims().setSubject(user.getUsername());
	        claims.put("userId", user.getId());
	        claims.put("role", user.getRole());

	        byte[] signingKey = generateSigningKey();

	        return Jwts.builder()
	                .setClaims(claims)
	                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS256)
	                .compact();
	    }

	    private byte[] generateSigningKey() {
	        // Lógica para generar una clave de firma (signing key) segura
	        // ...
	        return "mySecretKey".getBytes(); // Cambiar por una implementación segura
	    }
	
	 
	 
}
