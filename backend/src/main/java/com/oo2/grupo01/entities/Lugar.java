package com.oo2.grupo01.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "lugar")

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lugar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include()
	@Setter(AccessLevel.PROTECTED)
	protected Long idLugar;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
	protected Set<Sensor> sensores;

	protected String nombreLugar;

	public Lugar(String nombreLugar) {
		this.nombreLugar = nombreLugar;
	}

}
