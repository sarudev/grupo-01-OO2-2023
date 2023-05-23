package com.oo2.grupo01.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "edificio")
@Getter
@Setter
@NoArgsConstructor
public class Edificio extends Lugar {

	@OneToMany(mappedBy = "edificio")
	private Set<Aula> aulas;

	public Edificio(String nombreLugar, Set<Aula> aulas) {
		super(nombreLugar);
		this.aulas = aulas;
	}

}
