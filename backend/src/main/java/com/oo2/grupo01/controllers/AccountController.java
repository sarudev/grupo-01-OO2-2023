package com.oo2.grupo01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oo2.grupo01.Utils.JWT;
import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.controllers.account.AccountLogin;
import com.oo2.grupo01.controllers.account.AccountLogout;
import com.oo2.grupo01.controllers.account.AccountUserData;
import com.oo2.grupo01.entities.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/account")
public class AccountController {
  @Autowired
  private AccountLogin login;

  @Autowired
  private AccountLogout logout;

  @Autowired
  private AccountUserData userData;

  @Autowired
  HttpServletRequest req;

  @Autowired
  HttpServletResponse res;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User user) {
    return login.login(res, user);
  }

  @AuthRole("user")
  @GetMapping("/userData")
  public ResponseEntity<?> userData() throws Exception {
    System.out.println(JWT.getJwt(req));
    return userData.userData(req);
  }

  @GetMapping("/logout")
  public ResponseEntity<?> logout() {
    return logout.logout(req, res);
  }
}
