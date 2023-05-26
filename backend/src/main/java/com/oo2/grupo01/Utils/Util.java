package com.oo2.grupo01.Utils;

import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.models.SensorBascula;
import com.oo2.grupo01.models.SensorCamara;
import com.oo2.grupo01.models.SensorHumedad;
import com.oo2.grupo01.models.SensorTiempo;

public class Util {
  public static Sensor convertirSensor(Sensor sensor) {
    Sensor retorno = null;

    switch (sensor.getTipo()) {
      case BASCULA:
        retorno = new SensorBascula(sensor);
        break;
      case CAMARA:
        retorno = new SensorCamara(sensor);
        break;
      case HUMEDAD:
        retorno = new SensorHumedad(sensor);
        break;
      case TIEMPO:
        retorno = new SensorTiempo(sensor);
        break;
    }
    return retorno;
  }
}
