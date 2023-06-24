package com.oo2.grupo01.services;

import com.oo2.grupo01.entities.User;

public interface IUserService {
  public User findByUsername(String username);

  public void add(User user);

  public String encodePassword(String password);
}
