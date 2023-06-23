package com.oo2.grupo01.controllers.account;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.oo2.grupo01.entities.User;
import com.oo2.grupo01.services.implementacion.UserService;

@Component
public class AccountLogin {
  @Autowired
  private UserService userService;

  public ResponseEntity<?> login(HttpServletResponse res, @RequestBody User user) {
    String username = user.getUsername();
    String password = user.getPassword();

    User storedUser = userService.findByUsername(username);

    if (storedUser == null || !userService.isPasswordCorrect(storedUser, password)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    String token = userService.generateToken(storedUser);

    Cookie jwtCookie = new Cookie("JWT", token);
    jwtCookie.setHttpOnly(true);
    jwtCookie.setPath("/");

    res.addCookie(jwtCookie);

    return ResponseEntity.status(HttpStatus.OK).body("login");
  }
}
