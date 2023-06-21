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

// import com.oo2.grupo01.dto.EstacionamientoDTO;
// import com.oo2.grupo01.dto.ParkingDTO;
// import com.oo2.grupo01.entities.Estacionamiento;
// import com.oo2.grupo01.entities.Lugares;
// import com.oo2.grupo01.entities.Parking;
// import com.oo2.grupo01.entities.Sensores;
// import com.oo2.grupo01.services.implementacion.EstacionamientoService;
// import com.oo2.grupo01.services.implementacion.ParkingService;
// import com.oo2.grupo01.services.implementacion.RegistroService;
// import com.oo2.grupo01.services.implementacion.SensorService;

// import lombok.AllArgsConstructor;

// @Controller("parkingController")
// @RequestMapping("/parkings")
// @AllArgsConstructor
// public class ParkingController {
// private ParkingService parkingService;
// private EstacionamientoService estacionamientoService;
// private SensorService sensorService;
// private RegistroService registroService;

// @PostMapping
// public ResponseEntity<Object> crearParking(@RequestBody String ubicacion) {
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

// try {
// parkingService.agregar(Lugares.PARKING, ubicacion);
// httpStatus = HttpStatus.CREATED;
// } catch (Exception e) {
// e.printStackTrace();
// }

// return new ResponseEntity<Object>(httpStatus);
// }

// @PostMapping("/{id}")
// public ResponseEntity<Object> crearEstacionamiento(@PathVariable(name = "id")
// Long idParking,
// @RequestBody Integer numero) {
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

// try {
// estacionamientoService.agregar(Lugares.ESTACIONAMIENTO,
// parkingService.traer(idParking), numero);
// httpStatus = HttpStatus.CREATED;
// } catch (Exception e) {
// e.printStackTrace();
// }

// return new ResponseEntity<Object>(httpStatus);
// }

// @GetMapping
// public ResponseEntity<List<ParkingDTO>> traerTodosLosParkings() {
// return new ResponseEntity<List<ParkingDTO>>(parkingService.traerTodos(),
// HttpStatus.OK);
// }

// @GetMapping("/{id}")
// public ResponseEntity<ParkingDTO> traerParking(@PathVariable(name = "id")
// Long id) {
// Parking parking = parkingService.traerConDependencias(id);
// ParkingDTO pDTO = null;

// if (parking != null) {
// pDTO = new ParkingDTO(parking);
// pDTO.inicializarVariables();
// registroService.agregar(pDTO.getSensores());
// }

// return new ResponseEntity<ParkingDTO>(pDTO, HttpStatus.OK);
// }

// @GetMapping("/{idParking}/{idEstacionamiento}")
// public ResponseEntity<EstacionamientoDTO> traerEstacionamiento(
// @PathVariable(name = "idEstacionamiento") Long idEstacionamiento,
// @PathVariable(name = "idParking") Long idParking) {
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
// EstacionamientoDTO eDTO = null;

// if (parkingService.traer(idParking) != null) {
// Estacionamiento estacionamiento =
// estacionamientoService.traerConDependencias(idEstacionamiento);
// if (estacionamiento != null) {
// eDTO = new EstacionamientoDTO(estacionamiento);
// eDTO.inicializarVariables();
// registroService.agregar(eDTO.getSensores());
// }
// }

// return new ResponseEntity<EstacionamientoDTO>(eDTO, httpStatus);
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<Object> eliminarParking(@PathVariable(name = "id") Long
// id) {
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

// try {
// parkingService.eliminar(id);
// httpStatus = HttpStatus.OK;
// } catch (Exception e) {
// e.printStackTrace();
// }

// return new ResponseEntity<Object>(httpStatus);
// }

// @DeleteMapping("/{id}/{idEstacionamiento}")
// public ResponseEntity<Object> eliminarEstacionamiento(@PathVariable(name =
// "id") Long id,
// @PathVariable(name = "id") Long idEstacionamiento) {
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

// try {
// estacionamientoService.eliminar(id);
// httpStatus = HttpStatus.OK;
// } catch (Exception e) {
// e.printStackTrace();
// }

// return new ResponseEntity<Object>(httpStatus);
// }

// @PostMapping("/{idParking}/sensor")
// public ResponseEntity<Object> agregarSensorAParking(@PathVariable(name =
// "idParking") Long id,
// @RequestBody Sensores tipo) {
// Parking parking = parkingService.traer(id);
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

// try {
// sensorService.agregar(tipo, parking);
// httpStatus = HttpStatus.CREATED;
// } catch (Exception e) {
// // TODO: handle exception
// e.printStackTrace();
// }
// return new ResponseEntity<Object>(httpStatus);
// }

// @PostMapping("/{idParking}/{idEstacionamiento}/sensor")
// public ResponseEntity<Object>
// agregarSensorAEstacionamiento(@PathVariable(name = "idParking") Long
// idParking,
// @PathVariable(name = "idEstacionamiento") Long idEstacionamiento,
// @RequestBody Sensores tipo) {
// Estacionamiento estacionamiento =
// estacionamientoService.traer(idEstacionamiento);
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

// try {
// sensorService.agregar(tipo, estacionamiento);
// httpStatus = HttpStatus.CREATED;
// } catch (Exception e) {
// // TODO: handle exception
// e.printStackTrace();
// }
// return new ResponseEntity<Object>(httpStatus);
// }

// }
