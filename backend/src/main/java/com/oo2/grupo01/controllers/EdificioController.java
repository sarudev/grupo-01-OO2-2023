package com.oo2.grupo01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.dto.ErrorDTO;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.models.SensorCamara;
import com.oo2.grupo01.models.SensorTemperatura;
import com.oo2.grupo01.models.SensorTiempo;
import com.oo2.grupo01.repositories.IEdificioRepository;
import com.oo2.grupo01.services.AulaService;
import com.oo2.grupo01.services.EdificioService;
import com.oo2.grupo01.services.LugarService;
import com.oo2.grupo01.services.SensorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/edificio")
public class EdificioController {
  @Autowired
  HttpServletRequest req;

  @Autowired
  HttpServletResponse res;

  @Autowired
  EdificioService edificioService;

  @Autowired
  AulaService aulaService;

  @Autowired
  LugarService lugarService;
  
  @Autowired
  IEdificioRepository repo;
  
  @Autowired
  SensorService sensorService;

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll() {
    var edificios = edificioService.getAll();

    return ResponseEntity.ok(edificioService.toDtoList(edificios));
  }

  @AuthRole("user")
  @GetMapping("/{idLugar}")
  public ResponseEntity<?> get(@PathVariable("idLugar") String idLugar) {
    Long id;

    try {
      id = Long.parseLong(idLugar);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("'idLugar' it's not a Long"));
    }

    var ed = edificioService.get(id);

    if (ed == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Edificio not found"));
    }

    return ResponseEntity.ok(ed);
    // return ResponseEntity.ok(new EdificioDTO(ed, true));
  }

  @AuthRole("admin")
  @PostMapping("/{idLugar}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("idLugar") String idLugar, @RequestBody String tipo) {
    // validar tipo de sensor ya que llega como un string
	  // crear un sensor del tipo que llega en el @RequestBody
	  
	  
	  System.out.println("ENTRE 1");
	  Long id = Long.parseLong(idLugar);
	  if(tipo.equalsIgnoreCase(Sensores.camara.toString())) {
		  SensorCamara sensorCamara = new SensorCamara(new Sensor(Sensores.camara, edificioService.get(id)));
		  sensorService.addSensor(sensorCamara);
	  }else if(tipo.equalsIgnoreCase(Sensores.temperatura.toString())) {
		  SensorTemperatura sensorTemperatura = new SensorTemperatura(new Sensor(Sensores.temperatura, edificioService.get(id)));
		  sensorService.addSensor(sensorTemperatura);
	  }else if(tipo.equalsIgnoreCase(Sensores.tiempo.toString())) {
		  System.out.println("Entre 5");
		  SensorTiempo sensorTiempo = new SensorTiempo(new Sensor(Sensores.tiempo, edificioService.get(id)));
		  System.out.println("entre 4");
		  sensorService.addSensor(sensorTiempo);
	  }

    // crear/insertar el sensor en el lugar idLugar
    // devolver ResponseEntity.ok("");

    // en caso de que el lugar sea una dependencia (aula / estacionamiento)
    // seguir los mismos pasos pero usar la id idDependencia en lugar de idLugar

	  System.out.println("ENTRE 3");
    return ResponseEntity.ok("admin post sensor edificio");
  }
}
