package com.oo2.grupo01.models;

import lombok.Getter;

@Getter
public class UserData {
  private int id;
  private String username;
  private String role;

  static public Integer roleToValue(String role) {
    if (role.toLowerCase().equals("admin"))
      return 1;
    if (role.toLowerCase().equals("user"))
      return 0;
    if (role.toLowerCase().equals(""))
      return -1;
    return null;
  }
}
