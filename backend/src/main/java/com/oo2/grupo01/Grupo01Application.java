package com.oo2.grupo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Grupo01Application {
  public static void main(String[] args) {
    SpringApplication.run(Grupo01Application.class, args);
    System.out.println("ok");
  }
  
  

}
