package com.oo2.grupo01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "espacioVerde")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EspacioVerde extends Lugar {

	@Column(name = "baja")
	private boolean baja;

	public EspacioVerde(String nombreLugar, boolean baja) {
		super(nombreLugar);
		this.baja = baja;
	}

}
