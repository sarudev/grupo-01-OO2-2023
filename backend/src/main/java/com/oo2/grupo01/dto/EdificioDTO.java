package com.oo2.grupo01.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.entities.Historial;
import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class EdificioDTO extends LugarDTO {
  private Boolean luces = null;
  private List<AulaDTO> aulas = new ArrayList<>();

  public static List<Sensores> allowedSensores = Arrays.asList(new Sensores[] { Sensores.tiempo });

  public EdificioDTO(Edificio edif, boolean conAulas) {
    super(edif.getIdLugar(), edif.getNombre(), edif.getTipo(), edif.getSensores(),
        edif.getHistorial());
    if (conAulas)
      this.aulas = edif.getAulas().stream().map(au -> new AulaDTO(au, false)).toList();
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
          Historial nocheLuces = null;

          var lastHistorialWasToday6 = historial.stream().anyMatch(h -> h.getFecha().isEqual(diaToday));
          var lastHistorialWasToday18 = historial.stream().anyMatch(h -> h.getFecha().isEqual(nocheToday));

          // si ahora es despues de las 6
          if (now.isAfter(diaToday)) {
            // si el último registro no fue hoy a las 6
            if (!lastHistorialWasToday6) {
              // crear registro para hoy a las 6
              diaLuces = new Historial(lugar, sen.getTipo(), "Se apagaron las luces", diaToday);
            }
            this.luces = false;
            // si ahora es despues de las 18
            if (now.isAfter(nocheToday)) {
              // si el último registro no fue hoy a las 18
              if (!lastHistorialWasToday18) {
                // crear registro para hoy a las 18
                nocheLuces = new Historial(lugar, sen.getTipo(), "Se encendieron las luces", nocheToday);
              }
              this.luces = true;
            }
          } else {
            this.luces = true;
          }

          Historial[] historiales = { diaLuces, nocheLuces };

          for (Historial h : historiales) {
            if (h != null) {
              // save in db
              lambda.save(h);
              // add to this.historial
              this.historial.add(h);
            }
          }
        } else {
        }
      }
    }
  }

}
