// package com.oo2.grupo01.controllers;

// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.oo2.grupo01.dto.EspacioVerdeDTO;
// import com.oo2.grupo01.entities.EspacioVerde;
// import com.oo2.grupo01.entities.Lugares;
// import com.oo2.grupo01.entities.Sensores;
// import com.oo2.grupo01.services.implementacion.EspacioVerdeService;
// import com.oo2.grupo01.services.implementacion.RegistroService;
// import com.oo2.grupo01.services.implementacion.SensorService;

// import lombok.AllArgsConstructor;

// @Controller("espacioVerdeController")
// @RequestMapping("/espacioverde")
// @AllArgsConstructor
// public class EspacioVerdeController {
// private EspacioVerdeService espacioVerdeService;
// private SensorService sensorService;
// private RegistroService registroService;

// @PostMapping
// public ResponseEntity<Object> crearEspacioVerde(@RequestBody String
// ubicacion) {
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

// try {
// espacioVerdeService.agregar(Lugares.ESPACIO_VERDE, ubicacion);
// httpStatus = HttpStatus.CREATED;
// } catch (Exception e) {
// e.printStackTrace();
// }

// return new ResponseEntity<Object>(httpStatus);
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<Object> eliminarEspacioVerde(@PathVariable(name = "id")
// Long id) {
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

// try {
// espacioVerdeService.eliminar(id);
// httpStatus = HttpStatus.OK;
// } catch (Exception e) {
// e.printStackTrace();
// }

// return new ResponseEntity<Object>(httpStatus);
// }

// @GetMapping
// public ResponseEntity<List<EspacioVerdeDTO>> traerTodos() {
// return new
// ResponseEntity<List<EspacioVerdeDTO>>(espacioVerdeService.traerTodos(),
// HttpStatus.OK);
// }

// @GetMapping("/{id}")
// public ResponseEntity<EspacioVerdeDTO>
// traerConDependencias(@PathVariable(name = "id") Long id) {
// EspacioVerde espacioVerde = espacioVerdeService.traerConDependencias(id);
// EspacioVerdeDTO eVDto = null;

// // se crean los registros de esta solicitud a este espacio verde
// if (espacioVerde != null) {
// eVDto = new EspacioVerdeDTO(espacioVerde);
// eVDto.inicializarVariables();
// registroService.agregar(eVDto.getSensores());
// }

// return new ResponseEntity<EspacioVerdeDTO>(eVDto, HttpStatus.OK);
// }

// @PostMapping("/{id}")
// public ResponseEntity<Object> agregarSensorAEspacioVerde(@PathVariable(name =
// "id") Long id,
// @RequestBody Sensores tipo) {
// EspacioVerde espacioVerde = espacioVerdeService.traer(id);
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

// try {
// sensorService.agregar(tipo, espacioVerde);
// httpStatus = HttpStatus.CREATED;
// } catch (Exception e) {
// // TODO: handle exception
// e.printStackTrace();
// }
// return new ResponseEntity<Object>(httpStatus);
// }

// }
