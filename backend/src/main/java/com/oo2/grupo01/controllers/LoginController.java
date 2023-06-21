package com.oo2.grupo01.controllers;

import jakarta.servlet.http.HttpServletResponse;

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

@RestController
public class LoginController {

  private final UserService userService;

  public LoginController(IUserRepository userRepository, UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {
    String username = user.getUsername();
    String password = user.getPassword();

    User storedUser = userService.findByUsername(username);

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

    return ResponseEntity.status(HttpStatus.OK).body("");
  }
}
