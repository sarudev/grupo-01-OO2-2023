package com.oo2.grupo01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "estacionamiento")

@Getter
@Setter
@NoArgsConstructor
public class Estacionamiento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idEstacionamiento;

  @Column(name = "baja")
  private boolean baja;

  // fk parking
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_parking")
  private Parking parking;

  // fk lugar
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_lugar")
  private Lugar lugar;

  public Estacionamiento(int idEstacionamiento, boolean baja, Parking parking, Lugar lugar) {
    super();
    this.baja = baja;
    this.parking = parking;
    this.lugar = lugar;
  }

}
