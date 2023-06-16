import { type ILugarTipo, type SensorType } from './enums'

export type Lugares = IEdificio | IAula | IEspacioVerde | IParking | IEstacionamiento
export type Dependencia = IAula[] | IEstacionamiento[] | null
export type DependenciaTipo = 'aula' | 'estacionamiento'

export interface ILugar {
  id: number
  nombre: string
  luces: boolean
  historial: IHistorial[]
  sensores: ISensor[]
}

export interface IEdificio extends ILugar {
  tipo: ILugarTipo.Edificio
  aulas: IAula[]
  lugar: null
}

export interface IAula extends ILugar {
  tipo: ILugarTipo.Aula
  lugar: IEdificio
}

export interface IEspacioVerde extends ILugar {
  tipo: ILugarTipo.EspacioVerde
  lugar: null
}

export interface IParking extends ILugar {
  tipo: ILugarTipo.Parking
  estacionamientos: IEstacionamiento[]
  lugar: null
}

export interface IEstacionamiento extends ILugar {
  tipo: ILugarTipo.Estacionamiento
  lugar: IParking
}

export interface ICampus {
  edificios: IEdificio[]
  espaciosVerdes: IEspacioVerde[]
  parkings: IParking[]
}

export interface ISensor {
  id: number
  tipo: SensorType
  edificioTipo: string
  activo: boolean
}

export interface IHistorial {
  sensorTipo: SensorType
  descripcion: string
  edificioTipo: string
  fecha: string
}
