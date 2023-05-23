package com.oo2.grupo01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "estacionamiento")

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Estacionamiento extends Lugar {

	@Column(name = "baja")
	private boolean baja;

	// fk parking
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_parking")
	private Parking parking;

	public Estacionamiento(String nombreLugar, boolean baja) {
		super(nombreLugar);
		this.baja = baja;
	}

}
