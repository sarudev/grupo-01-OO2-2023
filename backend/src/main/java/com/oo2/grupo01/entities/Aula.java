package com.oo2.grupo01.entities;

import com.oo2.grupo01.entities.enums.Lugares;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "aula")
@PrimaryKeyJoinColumn(referencedColumnName = "idLugar")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Aula extends Lugar {
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_edificio")
  private Edificio lugar;

  public Aula(Edificio lugar, String nombre) throws Exception {
    super(Lugares.aula, nombre);
    if (lugar == null)
      throw new Exception("edificio null");
    this.lugar = lugar;
  }
}
