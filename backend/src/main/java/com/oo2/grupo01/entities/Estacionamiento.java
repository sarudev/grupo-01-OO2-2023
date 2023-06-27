package com.oo2.grupo01.entities;

import com.oo2.grupo01.entities.enums.Lugares;

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
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Estacionamiento extends Lugar {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_parking")
	private Parking lugar;

	public Estacionamiento(Parking lugar, String numero) {
		super(Lugares.estacionamiento, numero);
		this.lugar = lugar;
	}
}
