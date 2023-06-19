package com.oo2.grupo01.controllers;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.entities.User;
import com.oo2.grupo01.repositories.IUserRepository;
import com.oo2.grupo01.services.implementacion.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;


@RestController
public class LoginController {
	
	
	 private static final String JWT_SECRET = System.getenv("JWT_SECRET");

	    private final IUserRepository userRepository;
	    private final UserService userService;

	    public LoginController(IUserRepository userRepository, UserService userService) {
	        this.userRepository = userRepository;
	        this.userService = userService;
	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {
	        String username = user.getUsername();
	        String password = user.getPassword();

	        User storedUser = userRepository.findByUsername(username);

	        if (storedUser == null || !userService.isPasswordCorrect(storedUser, password)) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	        }

	        // Generate JWT token
	        String token = userService.generateToken(storedUser);

	        // Create and set the JWT token as a cookie
	        ResponseCookie cookie = ResponseCookie.from("JWT", token)
	                .httpOnly(true)
	                .build();

	        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

	        Map<String, String> responseBody = new HashMap<>();
	        responseBody.put("token", token);

	        return ResponseEntity.ok(responseBody);
	    }
}