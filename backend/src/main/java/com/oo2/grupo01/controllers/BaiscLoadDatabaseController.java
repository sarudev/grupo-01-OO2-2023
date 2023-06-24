package com.oo2.grupo01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.Utils.DatabaseUtils;
import com.oo2.grupo01.entities.User;
import com.oo2.grupo01.entities.enums.UserRole;
import com.oo2.grupo01.services.implementacion.AulaService;
import com.oo2.grupo01.services.implementacion.EdificioService;
import com.oo2.grupo01.services.implementacion.EspacioVerdeService;
import com.oo2.grupo01.services.implementacion.EstacionamientoService;
import com.oo2.grupo01.services.implementacion.ParkingService;
import com.oo2.grupo01.services.implementacion.UserService;

@RestController
@RequestMapping("/loadDB")
public class BaiscLoadDatabaseController {
  @Autowired
  EdificioService edificioService;

  @Autowired
  ParkingService parkingService;

  @Autowired
  EspacioVerdeService espacioVerdeService;

  @Autowired
  AulaService aulaService;

  @Autowired
  EstacionamientoService estacionamientoService;

  @Autowired
  UserService userService;

  @Autowired
  DatabaseUtils db;

  @GetMapping
  public ResponseEntity<?> loadDB() throws Exception {
    String[] edificios = {
        "Néstor Kirchner",
        "Pascual Contursi",
        "Juana Azurduy",
        "Leopoldo Marechal",
        "Ortega Peña",
        "Homero Manzi",
        "Manuel Ugarte",
        "Arturo Jauretche",
        "Raúl Scalabrini Ortiz",
        "Macedonio Fernández",
        "Lisandro de la Torre",
        "Casa del Estudiante",
        "Cine Tita Merello",
        "Héctor Oesterheld",
        "Jardín Maternal A. Villaflor",
        "Oscar Varsavsky",
        "Leonardo Wethein",
        "Irma Laciar de Carrica",
        "Campo de deportes Delfo Cabrera",
        "Inadi",
        "Juana Manso",
        "Quincho Roberto Fontanarrosa",
        "Gimnasio Comunitario Gatica",
        "Hernandez Arregui",
        "Lola Mora",
        "Estudio de Grabación E.S. Discépolo",
        "Comedor Universitario Padre Mujica",
        "José Hernández"
    };

    String[] parkings = {
        "29 de Septiembre",
        "Pablo Nogués",
        "Comedor",
        "Canchas"
    };

    String[] espaciosVerdes = {
        "Ortiz y Jauretche",
        "Macedonio y Ortiz",
        "Casa del estudiante",
        "Jauretche y Ugarte",
        "Manso y Kirchner"
    };

    db.truncateAllTables();

    for (var edif : edificios) {
      edificioService.add(edif);
      System.out.println("edificio: " + edif);
    }

    for (var park : parkings) {
      parkingService.add(park);
      System.out.println("parking: " + park);
    }

    for (var espa : espaciosVerdes) {
      espacioVerdeService.add(espa);
      System.out.println("espacioVerde: " + espa);
    }

    var ed = edificioService.get("José Hernández");
    if (ed != null) {

      aulaService.add(ed, "11");
      System.out.println("aula: 11");

      aulaService.add(ed, "123123");
      System.out.println("aula: 123123");
    } else {
      System.out.println("\n\nEDIFICIO IS NULL\n\n");
    }

    userService.add(new User("user", userService.encodePassword("user"), UserRole.user, true));
    System.out.println("account: user");

    userService.add(new User("admin", userService.encodePassword("admin"), UserRole.admin, true));
    System.out.println("account: admin");

    return ResponseEntity.ok("Datos creados");
  }
}
