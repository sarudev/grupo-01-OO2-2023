package com.oo2.grupo01.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parking")

@Getter
@Setter
@NoArgsConstructor
public class Parking extends Lugar {

	@Column(name = "baja")
	private boolean baja;

	// fk estacionamiento
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parking")
	Set<Estacionamiento> estacionamientos;

	public Parking(String nombreLugar, boolean baja) {
		super(nombreLugar);
		this.baja = baja;
	}

}
