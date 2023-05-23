package com.oo2.grupo01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sensor")

@Getter
@Setter
@NoArgsConstructor
public class Sensor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSensor;

	@Column(name = "nombreSensor")
	private String nombreSensor;

	@Column(name = "activo")
	private boolean activo;

	// fk lugar
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lugar_id")
	Lugar lugar;

	public Sensor(String nombreSensor, boolean activo, Lugar lugar) {
		this.nombreSensor = nombreSensor;
		this.activo = activo;
		this.lugar = lugar;
	}

}
