package com.oo2.grupo01.entities;

import java.util.Set;

import com.oo2.grupo01.entities.enums.Lugares;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "parking")
@PrimaryKeyJoinColumn(referencedColumnName = "idLugar")

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Parking extends Lugar {
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
  Set<Estacionamiento> estacionamientos;

  public Parking(Lugares tipo, String ubicacion) {
    super(tipo, ubicacion);
  }
}
