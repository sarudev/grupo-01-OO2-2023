package com.oo2.grupo01.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.oo2.grupo01.entities.enums.Lugares;

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
@Table(name = "edificio")
@PrimaryKeyJoinColumn(referencedColumnName = "idLugar")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idLugar")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Edificio extends Lugar {
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
	private List<Aula> aulas = new ArrayList<>();

	public Edificio(String nombre) {
		super(Lugares.edificio, nombre);
	}
}
