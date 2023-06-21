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
@Table(name = "edificio")
@PrimaryKeyJoinColumn(referencedColumnName = "idLugar")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Edificio extends Lugar {
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
  private Set<Aula> aulas;

  public Edificio(Lugares tipo, String nombre) {
    super(tipo, nombre);
  }
}
