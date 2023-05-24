package com.oo2.grupo01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "estacionamiento")
@PrimaryKeyJoinColumn(referencedColumnName = "idLugar")

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Estacionamiento extends Lugar {

	// fk parking
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_parking")
	private Parking parking;

	@Column(name = "numero")
	private int numero;

	public Estacionamiento(Lugares lugar, Parking parking, int numero) {
		super( lugar);
		this.parking = parking;
		this.numero = numero;
	}

}
