package com.oo2.grupo01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aula")

@Getter
@Setter
@NoArgsConstructor
public class Aula extends Lugar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idAula;

  @Column(name = "baja")
  private boolean baja;

  @ManyToOne
  @JoinColumn(name = "id_edificio") // Nombre de la columna que actúa como clave externa en la tabla de Propietario
  private Edificio edificio;

  @ManyToOne
  @JoinColumn(name = "id_lugar") // Nombre de la columna que actúa como clave externa en la tabla de Propietario
  private Lugar lugar;

  public Aula(int idAula, String nombre, boolean baja, Edificio edificio, Lugar lugar) {
    this.baja = baja;
    this.edificio = edificio;
    this.lugar = lugar;
  }



}
