package com.oo2.grupo01.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lugar")

@Getter
@Setter
@NoArgsConstructor
public class Lugar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idLugar;

  @OneToMany(mappedBy = "lugar")
  private Set<Aula> aulas = new HashSet<Aula>();

  @OneToMany(mappedBy = "lugar")
  private Set<Edificio> edificios = new HashSet<Edificio>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
  private Set<EspacioVerde> espacioVerdes = new HashSet<EspacioVerde>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
  private Set<Estacionamiento> estacionamientos = new HashSet<Estacionamiento>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
  private Set<Parking> parkings = new HashSet<Parking>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
  private Set<Sensor> sensores = new HashSet<Sensor>();

  private String nombreLugar;



  public Lugar(String nombreLugar) {
    this.nombreLugar = nombreLugar;
  }

}
