import { type SensorType } from './enums'

export interface Campus {
  edificios: Edificio[]
  espaciosVerdes: EspacioVerde[]
  parkings: Parking[]
}

export interface Sensor {
  id: number
  tipo: SensorType
  activo: boolean
}

export type LugarTipo = 'aula' | 'edificio' | 'parking' | 'espacioVerde' | 'estacionamiento'
export type DependenciaTipo = 'aula' | 'estacionamiento'

export interface Historial {
  tipo: SensorType
  mensaje: string
  fecha: number
}

export interface LugarExtends {
  tipo: LugarTipo
  id: number
  nombre: string
  luces: boolean
  historial: Historial[]
  sensores: Sensor[]
  lugar: Edificio | Parking | null
}

export interface Aula extends LugarExtends {}

export interface Edificio extends LugarExtends {
  aulas: Aula[]
}

export interface EspacioVerde extends LugarExtends {}

export interface Estacionamiento extends LugarExtends {}

export interface Parking extends LugarExtends {
  estacionamientos: Estacionamiento[]
}

export type Lugar = Aula | Edificio | EspacioVerde | Estacionamiento | Parking
