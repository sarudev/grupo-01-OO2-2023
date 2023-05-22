package com.oo2.grupo01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="aula")
public class Aula{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAula;
	
	@Column(name="nombre")
	private String nombre;
	
	
	@Column(name="baja")
	private boolean baja;

	 @ManyToOne
	 @JoinColumn(name = "id_edificio") // Nombre de la columna que actúa como clave externa en la tabla de Propietario
	 private Edificio edificio;
	
	 @ManyToOne
	 @JoinColumn(name = "id_lugar") // Nombre de la columna que actúa como clave externa en la tabla de Propietario
	 private Lugar lugar;
	
	 
	 
	 
	public Aula() {}

	
	public Aula(int idAula, String nombre, boolean baja, Edificio edificio, Lugar lugar) {
		super();
		this.idAula = idAula;
		this.nombre = nombre;
		this.baja = baja;
		this.edificio = edificio;
		this.lugar = lugar;
	}



	public int getIdAula() {
		return idAula;
	}

	protected void setIdAula(int idAula) {
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

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}


	
	
	
	
	
	
}
