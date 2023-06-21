package com.oo2.grupo01.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.User;


@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable>{
	
	public User findByUsername(String username);
	
	//public  User findByUsernameAndRole(String username);

}
