package com.oo2.grupo01.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	private int idLugar;
	
	@OneToMany(mappedBy = "lugar")
	private Set<Aula> aulas =  new HashSet<Aula>();
	
	@OneToMany(mappedBy = "lugar")
	private Set<Edificio> edificios = new HashSet<Edificio>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "lugar")
	private Set<EspacioVerde> espacioVerdes = new HashSet<EspacioVerde>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "lugar")
	private Set<Estacionamiento> estacionamientos = new HashSet<Estacionamiento>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
	private Set<Parking> parkings = new HashSet<Parking>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
	private Set<Sensor> sensores = new HashSet<Sensor>();
	
	private String nombreLugar;

	public Lugar() {
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

	public Set<Edificio> getEdificios() {
		return edificios;
	}

	public void setEdificios(Set<Edificio> edificios) {
		this.edificios = edificios;
	}

	public Set<EspacioVerde> getEspacioVerdes() {
		return espacioVerdes;
	}

	public void setEspacioVerdes(Set<EspacioVerde> espacioVerdes) {
		this.espacioVerdes = espacioVerdes;
	}

	public Set<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}

	public void setEstacionamientos(Set<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;
	}

	public Set<Parking> getParkings() {
		return parkings;
	}

	public void setParkings(Set<Parking> parkings) {
		this.parkings = parkings;
	}

	public Set<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(Set<Sensor> sensores) {
		this.sensores = sensores;
	}

	public String getNombreLugar() {
		return nombreLugar;
	}

	public void setNombreLugar(String nombreLugar) {
		this.nombreLugar = nombreLugar;
	}

	
	


	
	
	
}
