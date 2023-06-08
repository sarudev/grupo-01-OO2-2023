export enum SensorType {
  Temperatura = 'Temperatura',
  Bascula = 'Bascula',
  Humedad = 'Humedad',
  Camara = 'Camara',
  Tiempo = 'Tiempo'
}

export const lugarDependencia: Record<string, string> = {
  edificio: 'aulas',
  parking: 'estacionamientos',
  espacioVerde: '',
  aula: '',
  estacionamiento: ''
}
