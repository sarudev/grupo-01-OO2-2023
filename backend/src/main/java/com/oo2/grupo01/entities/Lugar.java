package com.oo2.grupo01.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="lugar")
public class Lugar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idLugar;
	
	@OneToMany(mappedBy = "lugar")
	private Set<Aula> aulas;
	
	@OneToMany(mappedBy = "lugar")
	private Set<Edificio> edificio;
	
	private String nombreLugar;


	public Lugar() {
		super();
	}


	public Lugar(int idLugar, Set<Aula> aulas, Set<Edificio> edificio, String nombreLugar) {
		super();
		this.idLugar = idLugar;
		this.aulas = aulas;
		this.edificio = edificio;
		this.nombreLugar = nombreLugar;
	}


	public int getIdLugar() {
		return idLugar;
	}


	public void setIdLugar(int idLugar) {
		this.idLugar = idLugar;
	}


	public Set<Aula> getAulas() {
		return aulas;
	}


	public void setAulas(Set<Aula> aulas) {
		this.aulas = aulas;
	}


	public Set<Edificio> getEdificio() {
		return edificio;
	}


	public void setEdificio(Set<Edificio> edificio) {
		this.edificio = edificio;
	}


	public String getNombreLugar() {
		return nombreLugar;
	}


	public void setNombreLugar(String nombreLugar) {
		this.nombreLugar = nombreLugar;
	}
	
	
	
}
