// package com.oo2.grupo01.controllers;

// <<<<<<< HEAD
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// =======
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// >>>>>>> f2b5f8a6a90033a49afad50c507dc7b7300d919f

// import com.oo2.grupo01.services.implementacion.SensorService;

// import lombok.AllArgsConstructor;

// @Controller("sensorController")
// @RequestMapping("/sensor")
// @AllArgsConstructor
// public class SensorController {

// <<<<<<< HEAD
// private SensorService sensorService;
// =======
// private SensorService sensorService;

// /*
// * Además de tener en cuenta un posible traerConRegistros(Long id) para
// * una eventual página de un sensor x con todo lo que registró hasta el
// momento.
// *
// * Los agregar sensor se harán en los otros controllers porque esos ya
// disponen de los
// * services de los posibles lugares a los que puede ser agregado un sensor
// * */

// @DeleteMapping("/{id}")
// public ResponseEntity<Object> eliminarSensor(@PathVariable(name = "id")Long
// id){
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
// >>>>>>> f2b5f8a6a90033a49afad50c507dc7b7300d919f

// /*
// * Además de tener en cuenta un posible traerConRegistros(Long id) para
// * una eventual página de un sensor x con todo lo que registró hasta el
// momento.
// *
// * Los agregar sensor se harán en los otros controllers porque esos ya
// disponen de los
// * services de los posibles lugares a los que puede ser agregado un sensor
// * */

// <<<<<<< HEAD
// public ResponseEntity<Object> eliminarSensor(Long id){
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
// =======
// return new ResponseEntity<Object>(httpStatus);
// }

// @PostMapping("/{id}")
// public ResponseEntity<Object> switchSensor(@PathVariable(name = "id")Long
// id){
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
// >>>>>>> f2b5f8a6a90033a49afad50c507dc7b7300d919f

// try {
// sensorService.eliminar(id);
// httpStatus = HttpStatus.OK;
// } catch (Exception e) {
// e.printStackTrace();
// }

// return new ResponseEntity<Object>(httpStatus);
// }

// public ResponseEntity<Object> switchSensor(Long id){
// HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

// try {
// sensorService.switchSensor(id);
// httpStatus = HttpStatus.OK;
// } catch (Exception e) {
// e.printStackTrace();
// }

// return new ResponseEntity<Object>(httpStatus);
// }
// }
