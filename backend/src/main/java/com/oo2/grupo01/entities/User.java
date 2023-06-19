package com.oo2.grupo01.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter @Getter @NoArgsConstructor
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		
		private String username;
		
		private String password;
		
		private String role;
		
		public User(String username, String password, String role) {
			this.username = username;
			this.password = password;
			this.role = role;
		}
		
		
		
		
		
}
