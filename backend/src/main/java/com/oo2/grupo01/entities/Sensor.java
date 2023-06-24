package com.oo2.grupo01.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sensor")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idSensor")
public class Sensor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Setter(AccessLevel.PROTECTED)
  private Long idSensor;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private Sensores tipo;

  @Column(name = "activo")
  private Boolean activo;

  // fk lugar
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idLugar")
  @JsonManagedReference
  private Lugar lugar;

  public Sensor(Sensores tipo, Lugar lugar) {
    this.tipo = tipo;
    this.activo = null;
    this.lugar = lugar;
  }

  public Sensor(Sensor sensor) {
    this.idSensor = sensor.idSensor;
    this.tipo = sensor.tipo;
    this.activo = sensor.activo;
    this.lugar = sensor.lugar;
  }
}
