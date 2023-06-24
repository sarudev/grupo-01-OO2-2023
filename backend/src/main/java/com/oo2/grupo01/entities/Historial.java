package com.oo2.grupo01.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oo2.grupo01.entities.enums.Sensores;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "historial")
@PrimaryKeyJoinColumn(referencedColumnName = "idHistorial")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Historial {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include()
  @Setter(AccessLevel.PROTECTED)
  protected Long idHistorial;

  // planeo dejarlo como un enum y no hacer un join directamente con el sensor
  // ya que como no vamos a hacer el delete de sensores, no tenemos la
  // necesidad de borrar el historial
  @Column(name = "tipo", nullable = false)
  @Enumerated(EnumType.STRING)
  private Sensores tipo;

  @Column(name = "descripcion", nullable = false)
  private String descripcion;

  @Column(name = "fecha", nullable = false)
  private LocalDateTime fecha;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idLugar")
  @JsonIgnore
  private Lugar lugar;

  public Historial(Sensores tipo, String descripcion, LocalDateTime fecha) {
    this.tipo = tipo;
    this.descripcion = descripcion;
    this.fecha = fecha;
  }
}
