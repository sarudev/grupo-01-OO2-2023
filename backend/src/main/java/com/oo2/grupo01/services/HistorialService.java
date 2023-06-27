package com.oo2.grupo01.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.Utils.DescRegs;
import com.oo2.grupo01.dto.AulaDTO;
import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.dto.EspacioVerdeDTO;
import com.oo2.grupo01.dto.EstacionamientoDTO;
import com.oo2.grupo01.dto.LugarDTO;
import com.oo2.grupo01.dto.ParkingDTO;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.entities.Historial;
import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.repositories.IHistorialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistorialService {

	private final IHistorialRepository historialRepository;

	public void add(Historial historial) {
		historialRepository.save(historial);
	}

	public void agregarHistorial(EspacioVerde lugar, EspacioVerdeDTO espacioVerdeDTO) {
		Boolean luces = espacioVerdeDTO.getLuces();
		Boolean aspersores = espacioVerdeDTO.getAspersores();

		if (luces != null) {
			Historial hisLuces = null;
			String lucesDesc = "";
			if (luces) {
				lucesDesc = DescRegs.LUCES_ON;
			} else {
				lucesDesc = DescRegs.lUCES_OFF;
			}

			if (!hayHistorialReciente(espacioVerdeDTO, Sensores.tiempo)) {
				hisLuces = new Historial((Lugar) lugar, Sensores.tiempo, lucesDesc, LocalDateTime.now());

				add(hisLuces);
			}
		}

		if (aspersores != null) {
			Historial hisAspersores = null;
			String aspDesc = "";
			
			if (aspersores) {
				aspDesc = DescRegs.ASPERSORES_ON;
			} else {
				aspDesc = DescRegs.ASPERSORES_OFF;
			}

			if (!hayHistorialReciente(espacioVerdeDTO, Sensores.humedad)) {
				hisAspersores = new Historial((Lugar) lugar, Sensores.humedad, aspDesc, LocalDateTime.now());

				add(hisAspersores);
			}

		}

	}

	public void agregarHisotrial(Edificio lugar, EdificioDTO edificioDTO) {
		Boolean luces = edificioDTO.getLuces();

		if (luces != null) {
			Historial hisLuces = null;
			String lucesDesc = "";
			if (luces) {
				lucesDesc = DescRegs.LUCES_ON;
			} else {
				lucesDesc = DescRegs.lUCES_OFF;
			}

			if (!hayHistorialReciente(edificioDTO, Sensores.tiempo)) {
				hisLuces = new Historial((Lugar) lugar, Sensores.tiempo, lucesDesc, LocalDateTime.now());

				add(hisLuces);
			}

		}

	}

	public void agregarHistorial(Parking lugar, ParkingDTO parkingDTO) {
		Boolean luces = parkingDTO.getLuces();

		if (luces != null) {
			Historial hisLuces = null;
			String lucesDesc = "";
			if (luces) {
				lucesDesc = DescRegs.LUCES_ON;
			} else {
				lucesDesc = DescRegs.lUCES_OFF;
			}

			if (!hayHistorialReciente(parkingDTO, Sensores.tiempo)) {
				hisLuces = new Historial((Lugar) lugar, Sensores.tiempo, lucesDesc, LocalDateTime.now());

				add(hisLuces);
			}
		}
	}

	public void agregarHistorial(Estacionamiento lugar, EstacionamientoDTO estacionamientoDTO) {
		Boolean libre = estacionamientoDTO.getLibre();

		if (libre != null) {
			Historial hisLibre = null;
			String libreDesc = "";

			if (libre) {
				libreDesc = DescRegs.ESTACIONAMIENTO_LIBRE;
			} else {
				libreDesc = DescRegs.ESTACIONAMIENTO_OCUPADO;
			}

			if (!hayHistorialReciente(estacionamientoDTO, Sensores.bascula)) {
				hisLibre = new Historial((Lugar) lugar, Sensores.bascula, libreDesc, LocalDateTime.now());

				add(hisLibre);
			}

		}
	}

	public void agregarHistorial(Aula lugar, AulaDTO aulaDTO) {
		Boolean luces = aulaDTO.getLuces();
		Boolean estufa = aulaDTO.getEstufas();
		Boolean aireAcondicionado = aulaDTO.getAireAcondicionado();

		if (luces != null) {
			Historial hisLuces = null;
			String lucesDesc = "";
			if (luces) {
				lucesDesc = DescRegs.LUCES_ON;
			} else {
				lucesDesc = DescRegs.lUCES_OFF;
			}

			if (!hayHistorialReciente(aulaDTO, Sensores.tiempo)) {
				hisLuces = new Historial((Lugar) lugar, Sensores.tiempo, lucesDesc, LocalDateTime.now());

				add(hisLuces);
			}
		}

		if (estufa != null && aireAcondicionado != null) {
			Historial hisEstufa = null;
			Historial hisAire = null;

			String estufaDesc = DescRegs.ESTUFA_OFF;
			String aireDesc = DescRegs.AIRE_OFF;

			if (estufa) {
				estufaDesc = DescRegs.ESTUFA_ON;
			}

			if (aireAcondicionado) {
				aireDesc = DescRegs.AIRE_ON;
			}

			if (!hayHistorialReciente(aulaDTO, Sensores.temperatura)) {
				hisEstufa = new Historial((Lugar) lugar, Sensores.temperatura, estufaDesc, LocalDateTime.now());
				hisAire = new Historial((Lugar) lugar, Sensores.temperatura, aireDesc, LocalDateTime.now());

				add(hisEstufa);
				add(hisAire);
			}

		}

	}

	private boolean hayHistorialReciente(LugarDTO dto, Sensores tipoSensor) {
		// si el tipo coincide y la diferencia de tiempo es menor o igual a 3 minutos, el
		// registro no se va a hacer.
		return dto.getHistorial().stream().anyMatch(
				t -> ChronoUnit.MINUTES.between(t.getFecha(), LocalDateTime.now()) <= 3 && t.getTipo() == tipoSensor);
	}

}
