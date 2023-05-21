package com.oo2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="edificio")
public class Edificio extends Lugar {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idEdificio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_aula", nullable=true)
	private Aula aula;
	
	
	@Column(name="nombre")
	private String nombre;
	
	public Edificio() {
	}

	public Edificio(int idEdificio, Aula aula, String nombre) {
		this.idEdificio = idEdificio;
		this.aula = aula;
		this.nombre = nombre;
	}

	public int getIdEdificio() {
		return idEdificio;
	}

	public void setIdEdificio(int idEdificio) {
		this.idEdificio = idEdificio;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
