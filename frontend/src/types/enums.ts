import { type DependenciaTipo, type LugarTipo } from './types'

export enum SensorType {
  Temperatura = 'Temperatura',
  Bascula = 'Bascula',
  Humedad = 'Humedad',
  Camara = 'Camara',
  Tiempo = 'Tiempo'
}

export const lugarDependencia: Record<LugarTipo, string | null> = {
  edificio: 'aulas',
  parking: 'estacionamientos',
  espacioVerde: null,
  aula: null,
  estacionamiento: null
}
