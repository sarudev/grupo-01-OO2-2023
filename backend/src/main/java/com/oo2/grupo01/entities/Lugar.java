package com.oo2.grupo01.entities;

import java.util.List;

import com.oo2.grupo01.entities.enums.Lugares;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "lugar")
@PrimaryKeyJoinColumn(referencedColumnName = "idLugar")
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

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "tipo", nullable = false)
  @Enumerated(EnumType.STRING)
  protected Lugares tipo;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
  private List<Sensor> sensores;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
  private List<Historial> historial;

  public Lugar(Lugares tipo, String nombre) {
    this.tipo = tipo;
    this.nombre = nombre;
  }
}
