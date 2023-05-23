package com.oo2.grupo01.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "edificio")

@Getter
@Setter
@NoArgsConstructor
public class Edificio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idEdificio;

  @OneToMany(mappedBy = "edificio")
  private Set<Aula> aulas;

  @ManyToOne
  @JoinColumn(name = "id_lugar") // Nombre de la columna que actúa como clave externa en la tabla de Propietario
  private Lugar lugar;


  public Edificio(int idEdificio, Lugar lugar) {
    this.lugar = lugar;
  }

  
}
