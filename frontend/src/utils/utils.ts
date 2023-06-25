import { ILugarTipo, Routes } from '../types/enums'
import { type Lugares } from '../types/types'

interface LugarDataset {
  lugarTipo: string
  lugarNum: string
  lugarNombre: string
}

export function getBuildingDataset (e: Event) {
  const elem = e.currentTarget as SVGGElement
  const dataset = elem.dataset as unknown as LugarDataset

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
  let url = Routes.BaseUrl as string
  if (lugar.tipo === ILugarTipo.Aula || lugar.tipo === ILugarTipo.Estacionamiento) url += `/${lugar.lugar.tipo}/${lugar.lugar.id}/${lugar.tipo}/${lugar.id}`
  else url += `/${lugar.tipo}/${lugar.id}`

  return url
}
