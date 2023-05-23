package com.oo2.grupo01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "espacioVerde")
@Getter
@Setter
@NoArgsConstructor
public class EspacioVerde extends Lugar {

	@Column(name = "baja")
	private boolean baja;

	public EspacioVerde(String nombreLugar, boolean baja) {
		super(nombreLugar);
		this.baja = baja;
	}

}
