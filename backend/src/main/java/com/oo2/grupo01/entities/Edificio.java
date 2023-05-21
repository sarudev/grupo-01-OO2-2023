package com.oo2.grupo01.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="edificio")
public class Edificio  {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idEdificio;

	@Column(name="nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "edificio")
	private Set<Aula> aulas;
	
	@ManyToOne
	@JoinColumn(name = "id_lugar") // Nombre de la columna que actúa como clave externa en la tabla de Propietario
	 private Lugar lugar;
	
	
	
	public Edificio() {
	}


	public Edificio(int idEdificio, String nombre, Set<Aula> aulas, Lugar lugar) {
		this.idEdificio = idEdificio;
		this.nombre = nombre;
		this.aulas = aulas;
		this.lugar = lugar;
	}



	public int getIdEdificio() {
		return idEdificio;
	}

	public void setIdEdificio(int idEdificio) {
		this.idEdificio = idEdificio;
	}

	public Set<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(Set<Aula> aulas) {
		this.aulas = aulas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	
	

	
	
	
}
