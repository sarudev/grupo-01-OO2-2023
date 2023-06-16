import { ILugarTipo } from '../types/enums'
import { type Lugares } from '../types/types'

const { VITE_API_URL } = import.meta.env as Record<string, string>

export function getBuildingDataset (e: Event) {
  const elem = e.currentTarget as SVGGElement
  const dataset = elem.dataset as { buildingName: string, buildingNumber: string }

  return {
    elem,
    dataset
  }
}

export function strToUrl (str: string) {
  return str.replaceAll(' ', '-')
}

export function urlToStr (str: string) {
  return str.replaceAll('-', ' ')
}

export function firstUpper (str: string) {
  return str.replace(str[0], str[0].toUpperCase())
}

export function apiUrl (lugar: Lugares) {
  let url = `http://${VITE_API_URL}:5282`
  if (lugar.tipo === ILugarTipo.Aula || lugar.tipo === ILugarTipo.Estacionamiento) url += `/${lugar.lugar.tipo}/${lugar.lugar.nombre.replaceAll(' ', '-')}/${lugar.tipo}/${lugar.nombre}`
  else url += `/${lugar.tipo}/${lugar.nombre.replaceAll(' ', '-')}`

  return url
}
