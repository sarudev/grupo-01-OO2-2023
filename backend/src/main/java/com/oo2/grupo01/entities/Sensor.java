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
@Table(name="sensor")
public class Sensor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSensor;
	
	private String nombreSensor;
	
	private boolean activo;
	
	//fk lugar
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lugar_id")
	Lugar lugar;

	public Sensor() {
	}

	public Sensor(String nombreSensor, boolean activo, Lugar lugar) {
		this.nombreSensor = nombreSensor;
		this.activo = activo;
		this.lugar = lugar;
	}

	public int getIdSensor() {
		return idSensor;
	}

	protected void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	public String getNombreSensor() {
		return nombreSensor;
	}

	public void setNombreSensor(String nombreSensor) {
		this.nombreSensor = nombreSensor;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
	
	
	
	
	
}
