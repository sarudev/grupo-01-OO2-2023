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

@Entity
@Table(name = "espacioVerde")
public class EspacioVerde {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idEspacioVerde;

  @Column(name = "baja")
  private boolean baja;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_lugar") // Nombre de la columna que actúa como clave externa en la tabla de Propietario
  private Lugar lugar;

  public EspacioVerde() {
  }

  public EspacioVerde(int idEspacioVerde, boolean baja, Lugar lugar) {
    this.baja = baja;
    this.lugar = lugar;
  }

  public int getIdEspacioVerde() {
    return idEspacioVerde;
  }

  protected void setIdEspacioVerde(int idEspacioVerde) {
    this.idEspacioVerde = idEspacioVerde;
  }

  public boolean isBaja() {
    return baja;
  }

  public void setBaja(boolean baja) {
    this.baja = baja;
  }

  public Lugar getLugar() {
    return lugar;
  }

  public void setLugar(Lugar lugar) {
    this.lugar = lugar;
  }

}
