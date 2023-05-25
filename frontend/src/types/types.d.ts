import { type SensorType } from './enums'

export interface Campus {
  edificios: Edificio[]
  espaciosVerdes: EspacioVerde[]
  parkings: Parking[]
}

export interface Sensor {
  id: number
  type: SensorType
}

export interface Lugar {
  id: number
  nombre: string
  sensores: Sensor[]
}

export interface Aula extends Lugar {}

export interface Edificio extends Lugar {
  aulas: Aula[]
}

export interface EspacioVerde extends Lugar {}

export interface Estacionamiento extends Lugar {}

export interface Parking extends Lugar {
  estacionamientos: Estacionamiento[]
}
