package com.oo2.grupo01.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.Utils.DatabaseUtils;
import com.oo2.grupo01.entities.User;
import com.oo2.grupo01.entities.enums.UserRole;
import com.oo2.grupo01.services.AulaService;
import com.oo2.grupo01.services.EdificioService;
import com.oo2.grupo01.services.EspacioVerdeService;
import com.oo2.grupo01.services.EstacionamientoService;
import com.oo2.grupo01.services.LoadedDBService;
import com.oo2.grupo01.services.ParkingService;
import com.oo2.grupo01.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BaiscLoadDatabaseController {
	private final EdificioService edificioService;
	private final ParkingService parkingService;
	private final EspacioVerdeService espacioVerdeService;
	private final AulaService aulaService;
	private final EstacionamientoService estacionamientoService;
	private final UserService userService;
	private final LoadedDBService loadedDbService;
	private final DatabaseUtils db;

	@GetMapping("/loadDB")
	public ResponseEntity<?> loadDB() throws Exception {
		var ok = ResponseEntity.ok("Datos creados");

		if (loadedDbService.loaded()) {
			return ok;
		}

		String[] edificios = { 
				"José Hernández",
				"Comedor Universitario Padre Mujica",
				"Estudio de Grabación E.S. Discépolo",
				"Lola Mora",
				"Hernandez Arregui",
				"Gimnasio Comunitario Gatica",
				"Quincho Roberto Fontanarrosa",
				"Juana Manso",
				"Inadi", 
				"Campo de deportes Delfo Cabrera",
				"Irma Laciar de Carrica", 
				"Leonardo Wethein", 
				"Oscar Varsavsky", 
				"Jardín Maternal A. Villaflor",
				"Héctor Oesterheld", 
				"Cine Tita Merello", 
				"Casa del Estudiante", 
				"Lisandro de la Torre",
				"Macedonio Fernández", 
				"Raúl Scalabrini Ortiz", 
				"Arturo Jauretche", 
				"Manuel Ugarte", 
				"Homero Manzi",
				"Ortega Peña", 
				"Leopoldo Marechal", 
				"Juana Azurduy", 
				"Pascual Contursi", 
				"Néstor Kirchner" 
				};

		String[] espaciosVerdes = { 
				"Manso y Kirchner", 
				"Jauretche y Ugarte", 
				"Casa del estudiante",
				"Macedonio y Ortiz", 
				"Ortiz y Jauretche" 
				};

		String[] parkings = { 
				"29 de Septiembre", 
				"Pablo Nogués", 
				"Comedor", 
				"Canchas"
				};

		db.truncateAllTables();

		for (var edif : edificios) {
			edificioService.add(edif);
			System.out.println("edificio: " + edif);
		}

		for (var espa : espaciosVerdes) {
			espacioVerdeService.add(espa);
			System.out.println("espacioVerde: " + espa);
		}

		for (var park : parkings) {
			parkingService.add(park);
			System.out.println("parking: " + park);
		}

		var ed = edificioService.get(1l);
		if (ed != null) {

			aulaService.add(ed, "11");
			System.out.println("aula: 11");

			aulaService.add(ed, "123123");
			System.out.println("aula: 123123");
		} else {
			System.out.println("\n\nEDIFICIO IS NULL\n\n");
		}

		var pa = parkingService.get(34l);
		if (pa != null) {

			estacionamientoService.add(pa, "123");
			System.out.println("estacionamiento: 123");

			estacionamientoService.add(pa, "321");
			System.out.println("estacionamiento: 321");
		} else {
			System.out.println("\n\nPARKING IS NULL\n\n");
		}

		userService.add(new User("user", userService.encodePassword("user"), UserRole.user, true));
		System.out.println("account: user");

		userService.add(new User("admin", userService.encodePassword("admin"), UserRole.admin, true));
		System.out.println("account: admin");

		loadedDbService.load();

		return ok;
	}

	@GetMapping("/loadedDB")
	public ResponseEntity<?> loadedDB() {
		return ResponseEntity.ok(loadedDbService.loaded());
	}
}
