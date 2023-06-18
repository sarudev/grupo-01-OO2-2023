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

  // PostAulaSensor = '/edificio/:buildingName/aula/:aulaName/sensor',
  // PostAula = '/edificio/:buildingName/aula',
  Aula = '/edificio/:buildingName/aula/:aulaName',

  // PostEdificioSensor = '/edificio/:buildingName/sensor',
  Edificio = '/edificio/:buildingName',

  // PutEdificioSensor = '/edificio/:buildingName/sensor',
  // PutAulaSensor = '/edificio/:buildingName/aula/:aulaName/sensor',

  Login = '/account/login',
  UserData = '/account/userData',
  Logout = '/account/logout'
}

export const enum UserRole {
  Admin = 'admin',
  User = 'user'
}
