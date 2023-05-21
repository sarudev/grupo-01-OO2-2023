package com.oo2.grupo01.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="parking")
public class Parking  {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idParking;
}
