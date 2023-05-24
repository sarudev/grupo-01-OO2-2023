package com.oo2.grupo01.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "parking")
@PrimaryKeyJoinColumn(referencedColumnName = "idLugar")

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper =  true)
public class Parking extends Lugar {
	
	@Column(name = "ubicacion")
	private String ubicacion;
	
	// fk estacionamiento
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parking")
	Set<Estacionamiento> estacionamientos;

	public Parking(Lugares lugar, String ubicacion) {
		super(lugar);
		this.ubicacion = ubicacion;
	}

	

}
