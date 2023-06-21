package com.oo2.grupo01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "espacioVerde")
@PrimaryKeyJoinColumn(referencedColumnName = "idLugar")

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EspacioVerde extends Lugar {

	@Column(name = "ubicacion")
	private String ubicacion;

	public EspacioVerde(Lugares lugar, String ubicacion) {
		super( lugar);
		this.ubicacion = ubicacion;
	}

	
	
}
