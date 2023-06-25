import { ILugarTipo } from './enums'
import { type IAula, type IEspacioVerde, type IEdificio, type Lugares, type IEstacionamiento, type IParking } from './types'

export const isEdificio = (x: Lugares): x is IEdificio => x.tipo === ILugarTipo.Edificio
export const isAula = (x: Lugares): x is IAula => x.tipo === ILugarTipo.Aula
export const isParking = (x: Lugares): x is IParking => x.tipo === ILugarTipo.Parking
export const isEstacionamiento = (x: Lugares): x is IEstacionamiento => x.tipo === ILugarTipo.Estacionamiento
export const isEspacioVerde = (x: Lugares): x is IEspacioVerde => x.tipo === ILugarTipo.EspacioVerde
