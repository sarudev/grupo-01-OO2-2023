package com.oo2.grupo01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.controllers.account.AccountLogin;
import com.oo2.grupo01.controllers.account.AccountLogout;
import com.oo2.grupo01.controllers.account.AccountUserData;
import com.oo2.grupo01.entities.User;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/account")
public class AccountController {
  @Autowired
  private AccountLogin login;

  @Autowired
  private AccountLogout logout;

  @Autowired
  private AccountUserData userData;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {
    return login.login(user, response);
  }

  @AuthRole("user")
  @GetMapping("/userData")
  public ResponseEntity<?> userData(@CookieValue(value = "JWT", required = false) Cookie jwtCookie,
      HttpServletResponse response, HttpServletRequest request) throws Exception {
    return userData.userData(jwtCookie, response, request);
  }

  @GetMapping("/logout")
  public ResponseEntity<?> logout(@CookieValue(value = "JWT", required = false) Cookie jwtCookie,
      HttpServletResponse response) {
    return logout.logout(jwtCookie, response);
  }
}
