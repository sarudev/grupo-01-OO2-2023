package com.oo2.grupo01.entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "edificio")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Edificio extends Lugar{

	@OneToMany(mappedBy = "edificio")
	private Set<Aula> aulas;

	public Edificio(String nombreLugar, Set<Aula> aulas) {
		super(nombreLugar);
		this.aulas = aulas;
	}

}
