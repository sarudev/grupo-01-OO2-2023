package com.oo2.grupo01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aula")

@Getter
@Setter
@NoArgsConstructor
public class Aula extends Lugar {

	@Column(name = "baja")
	private boolean baja;

	@ManyToOne
	@JoinColumn(name = "id_edificio") // Nombre de la columna que actúa como clave externa en la tabla de Propietario
	private Edificio edificio;

	public Aula(String nombreLugar, boolean baja, Edificio edificio) {
		super(nombreLugar);
		this.baja = baja;
		this.edificio = edificio;
	}

}
