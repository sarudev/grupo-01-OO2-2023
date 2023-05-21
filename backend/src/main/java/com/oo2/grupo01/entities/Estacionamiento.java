package com.oo2.grupo01.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="estacionamiento")
public class Estacionamiento  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idEstacionamiento;
	
}
