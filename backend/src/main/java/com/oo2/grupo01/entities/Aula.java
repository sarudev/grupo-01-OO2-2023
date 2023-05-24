package com.oo2.grupo01.entities;

import jakarta.persistence.Entity;
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
@Table(name = "aula")
@PrimaryKeyJoinColumn(referencedColumnName = "idLugar")

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Aula extends Lugar{

	@ManyToOne
	@JoinColumn(name = "id_edificio") // Nombre de la columna que actúa como clave externa en la tabla de Propietario
	private Edificio edificio;

	private String nombre;

	public Aula(Lugares lugar, Edificio edificio, String nombre) {
		super(lugar);
		this.edificio = edificio;
		this.nombre = nombre;
	}
	
	

}
