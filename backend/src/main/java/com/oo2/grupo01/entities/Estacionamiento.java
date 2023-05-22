package com.oo2.grupo01.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="estacionamiento")
public class Estacionamiento  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEstacionamiento;
	
	private boolean baja;
	
	//fk parking
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_parking")
	private Parking parking;
	
	//fk lugar
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_lugar")
	private Lugar lugar;
	
	private boolean libre;


	
	
	public Estacionamiento() {
	}


	public Estacionamiento(int idEstacionamiento, boolean baja, Parking parking, Lugar lugar, boolean libre) {
		super();
		this.baja = baja;
		this.parking = parking;
		this.lugar = lugar;
		this.libre = libre;
	}


	public int getIdEstacionamiento() {
		return idEstacionamiento;
	}


	protected void setIdEstacionamiento(int idEstacionamiento) {
		this.idEstacionamiento = idEstacionamiento;
	}


	public boolean isBaja() {
		return baja;
	}


	public void setBaja(boolean baja) {
		this.baja = baja;
	}


	public Parking getParking() {
		return parking;
	}


	public void setParking(Parking parking) {
		this.parking = parking;
	}


	public Lugar getLugar() {
		return lugar;
	}


	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}


	public boolean isLibre() {
		return libre;
	}


	public void setLibre(boolean libre) {
		this.libre = libre;
	}

	
}
