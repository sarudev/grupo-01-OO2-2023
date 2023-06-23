package com.oo2.grupo01.Utils;

import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.models.SensorBascula;
import com.oo2.grupo01.models.SensorCamara;
import com.oo2.grupo01.models.SensorHumedad;
import com.oo2.grupo01.models.SensorTemperatura;
import com.oo2.grupo01.models.SensorTiempo;

public class Util {
  public static Sensor convertirSensor(Sensor sensor) {
    Sensor retorno = null;

    switch (sensor.getTipo()) {
      case bascula:
        retorno = new SensorBascula(sensor);
        break;
      case camara:
        retorno = new SensorCamara(sensor);
        break;
      case humedad:
        retorno = new SensorHumedad(sensor);
        break;
      case tiempo:
        retorno = new SensorTiempo(sensor);
        break;
      case temperatura:
        retorno = new SensorTemperatura(sensor);
        break;
    }
    return retorno;
  }

}
