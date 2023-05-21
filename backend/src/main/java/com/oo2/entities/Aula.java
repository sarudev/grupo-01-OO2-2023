package com.oo2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="aula")
public class Aula extends Lugar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAula;
	
	@Column(name="nombre")
	private String nombre;
	
	
	@Column(name="baja")
	private boolean baja;

	
	@Column(name="edificio")
	private Edificio edificio;
	
	public Aula() {}

	public Aula(int idAula, String nombre, boolean baja, Edificio edificio) {
		super();
		this.idAula = idAula;
		this.nombre = nombre;
		this.baja = baja;
		this.edificio = edificio;
	}
	
	public int getIdAula() {
		return idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
	
	
	
	
	
	
	
}
