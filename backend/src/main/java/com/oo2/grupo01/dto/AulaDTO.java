package com.oo2.grupo01.dto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.entities.Historial;
import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.models.SensorTemperatura;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class AulaDTO extends LugarDTO {
  private String nombre;
  private Boolean luces = null;
  private Boolean persianas = null;
  private Boolean aireAcondicionado = null;
  private Boolean estufas = null;
  private EdificioDTO lugar;

  public static List<Sensores> allowedSensores = Arrays
      .asList(new Sensores[] { Sensores.tiempo, Sensores.temperatura });

  public AulaDTO(Aula aula, boolean conLugar) {
    super(aula.getIdLugar(), aula.getNombre(), aula.getTipo(), aula.getSensores(), aula.getHistorial());
    this.nombre = aula.getNombre();

    if (conLugar)
      this.lugar = new EdificioDTO(aula.getLugar(), false);
  }

  @Override
  public void inicializarVariables(Lugar lugar, Util.SaveHistorial lambda) {
    for (var s : sensores) {
      if (s.isActivo()) {
        if (s instanceof SensorTiempo) {
          var sen = (SensorTiempo) s;
          var historial = this.getBy(s.getTipo());

          var now = LocalDateTime.now();
          LocalDateTime diaToday = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 8, 0);
          LocalDateTime nocheToday = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 18, 0);

          Historial diaLuces = null;
          Historial diaPersianas = null;
          Historial nocheLuces = null;
          Historial nochePersianas = null;

          var lastHistorialWasToday6 = historial.stream().anyMatch(h -> h.getFecha().isEqual(diaToday));
          var lastHistorialWasToday18 = historial.stream().anyMatch(h -> h.getFecha().isEqual(nocheToday));

          // si ahora es despues de las 6
          if (now.isAfter(diaToday)) {
            // si el último registro no fue hoy a las 6
            if (lastHistorialWasToday6) {
              // crear registro para hoy a las 6
              diaLuces = new Historial(lugar, sen.getTipo(), "Se apagaron las luces", diaToday);
              diaPersianas = new Historial(lugar, sen.getTipo(), "Se abrieron las persianas", diaToday);
            }
            this.luces = false;
            this.persianas = true;
            // si ahora es despues de las 18
            if (now.isAfter(nocheToday)) {
              // si el último registro no fue hoy a las 18
              if (lastHistorialWasToday18) {
                // crear registro para hoy a las 18
                nocheLuces = new Historial(lugar, sen.getTipo(), "Se encendieron las luces", nocheToday);
                nochePersianas = new Historial(lugar, sen.getTipo(), "Se cerraron las persianas", nocheToday);
              }
              this.luces = true;
              this.persianas = false;
            }
          } else {
            this.luces = true;
            this.persianas = false;
          }

          Historial[] historiales = { diaLuces, diaPersianas, nocheLuces, nochePersianas };

          for (Historial h : historiales) {
            if (h != null) {
              // save in db
              lambda.save(h);
              // add to this.historial
              this.historial.add(h);
            }
          }
        } else if (s instanceof SensorTemperatura) {
          if (Math.random() > 0.25) {
            this.estufas = Math.random() >= 0.5;
            this.aireAcondicionado = Math.random() >= 0.5;
            continue;
          }

          var sen = (SensorTemperatura) s;

          Historial aireAcondicionado = null;
          Historial estufa = null;

          var temp = sen.temperatura();

          if (temp < 13) {
            estufa = new Historial(lugar, sen.getTipo(), "Se encendió la estufa", LocalDateTime.now());
            this.estufas = true;
          } else if (temp >= 13) {
            estufa = new Historial(lugar, sen.getTipo(), "Se apagó la estufa", LocalDateTime.now());
            this.estufas = false;
          }

          if (temp > 25) {
            aireAcondicionado = new Historial(lugar, sen.getTipo(), "Se encendió el aire acondicionado",
                LocalDateTime.now());
            this.aireAcondicionado = true;
          } else if (temp <= 25) {
            aireAcondicionado = new Historial(lugar, sen.getTipo(), "Se apagó el aire acondicionado",
                LocalDateTime.now());
            this.aireAcondicionado = false;
          }

          Historial[] historiales = { aireAcondicionado, estufa };

          for (Historial h : historiales) {
            if (h != null) {
              // save in db
              lambda.save(h);
              // add to this.historial
              this.historial.add(h);
            }
          }
        } else {
          System.out.println("Invalid sensor: " + s.getTipo());
        }
      }
    }
  }
}
