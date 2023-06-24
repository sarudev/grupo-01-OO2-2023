package com.oo2.grupo01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.oo2.grupo01.entities.User;
import com.oo2.grupo01.repositories.IUserRepository;

@Service("userService")
public class UserService {

  @Autowired
  private IUserRepository userRepository;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public User findByUsername(String username) {
    System.out.println("Searching user by username: " + username);
    return userRepository.findByUsername(username);
  }

  public boolean isPasswordCorrect(User user, String password) {
    return passwordEncoder.matches(password, user.getPassword());
  }

  public void add(User user) {
    userRepository.save(user);
  }

  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
