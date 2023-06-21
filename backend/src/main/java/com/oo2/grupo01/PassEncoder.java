package com.oo2.grupo01;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassEncoder {
  public static void main(String[] args) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    var user = passwordEncoder.encode("user");
    var admin = passwordEncoder.encode("admin");
    System.out.println("insert into `user` values (1, 1, \"" + user + "\", \"USER\", \"user\");");
    System.out.println("insert into `user` values (2, 1, \"" + admin + "\", \"ADMIN\", \"admin\");");
  }
}
