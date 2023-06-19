export enum SensorType {
  Temperatura = 'Temperatura',
  Bascula = 'Bascula',
  Humedad = 'Humedad',
  Camara = 'Camara',
  Tiempo = 'Tiempo'
}

export enum ILugarTipo {
  Aula = 'aula',
  Edificio = 'edificio',
  Parking = 'parking',
  EspacioVerde = 'espacioVerde',
  Estacionamiento = 'estacionamiento'
}

export const LugarDependencia: Record<ILugarTipo, string | null> = {
  edificio: 'aulas',
  parking: 'estacionamientos',
  espacioVerde: null,
  aula: null,
  estacionamiento: null
}

export const enum Routes {
  BaseUrl = 'http://localhost:5282',

  Login = '/account/login',
  UserRole = '/account/userRole/:username',
  UserData = '/account/userData',
  Logout = '/account/logout',

  Sensor = '/sensor',
  Building = '/:buildingType/:buildingName',
  Dependency = '/:buildingType/:buildingName/:dependencyType/:dependencyName'
}

export const enum UserRole {
  Admin = 'admin',
  User = 'user'
}

export const lugarProperties = ['tipo', 'historial', 'id', 'lugar', 'lugarId', 'nombre', 'sensores', 'aulas', 'estacionamientos']
