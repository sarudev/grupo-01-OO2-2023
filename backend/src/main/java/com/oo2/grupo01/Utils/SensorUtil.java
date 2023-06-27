package com.oo2.grupo01.Utils;

import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.models.SensorBascula;
import com.oo2.grupo01.models.SensorHumedad;
import com.oo2.grupo01.models.SensorTemperatura;
import com.oo2.grupo01.models.SensorTiempo;

public class SensorUtil {
	public static Sensor convertirSensor(Sensor sensor) {
		Sensor retorno = null;

		switch (sensor.getTipo()) {
		case bascula:
			retorno = new SensorBascula(sensor);
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

	public static boolean isSensorTipo(String tipo) {
		return tipo.equalsIgnoreCase(Sensores.bascula.toString()) || tipo.equalsIgnoreCase(Sensores.humedad.toString())
				|| tipo.equalsIgnoreCase(Sensores.temperatura.toString())
				|| tipo.equalsIgnoreCase(Sensores.tiempo.toString());
	}

	public static Sensores toSensorTipo(String tipo) {
		if (tipo.equalsIgnoreCase(Sensores.bascula.toString())) {
			return Sensores.bascula;
		} else if (tipo.equalsIgnoreCase(Sensores.humedad.toString())) {
			return Sensores.humedad;
		} else if (tipo.equalsIgnoreCase(Sensores.temperatura.toString())) {
			return Sensores.temperatura;
		} else if (tipo.equalsIgnoreCase(Sensores.tiempo.toString())) {
			return Sensores.tiempo;
		} else {
			return null;
		}
	}
}
