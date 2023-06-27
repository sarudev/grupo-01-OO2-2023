package com.oo2.grupo01.entities;

import com.oo2.grupo01.entities.enums.Lugares;

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
	public EspacioVerde(String ubicacion) {
		super(Lugares.espacioVerde, ubicacion);
	}
}
