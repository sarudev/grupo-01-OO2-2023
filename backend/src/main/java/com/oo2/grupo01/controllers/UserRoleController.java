package com.oo2.grupo01.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.entities.User;
import com.oo2.grupo01.repositories.IUserRepository;

@RestController
@RequestMapping("/user-role")
public class UserRoleController {

  private final IUserRepository userRepository;

  public UserRoleController(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/{username}")
  public ResponseEntity<?> getUserRole(@PathVariable String username) {
    if (username == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("'username' is not a string or is missing in request body");
    }

    User user = userRepository.findByUsername(username);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
    }

    return ResponseEntity.ok().body("userRole: " + user.getRole());
  }

}
