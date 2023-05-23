package com.oo2.grupo01.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parking")

@Getter
@Setter
@NoArgsConstructor
public class Parking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idParking;

  @Column(name = "baja")
  private boolean baja;

  @Column(name = "ubicacion")
  private String ubicacion;

  // fk lugar
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_lugar")
  Lugar lugar;

  // fk estacionamiento
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parking")
  Set<Estacionamiento> estacionamientos = new HashSet<Estacionamiento>();

  public Parking(boolean baja, String ubicacion, Lugar lugar) {
    super();
    this.baja = baja;
    this.ubicacion = ubicacion;
    this.lugar = lugar;
  }

}
