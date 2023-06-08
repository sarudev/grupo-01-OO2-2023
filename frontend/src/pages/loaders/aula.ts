import { type LoaderFunctionArgs } from 'react-router-dom'
import { urlToStr } from '../../utils/utils'
import campus from '../../assets/campus'

export default async function aulaLoader ({ params }: LoaderFunctionArgs) {
  const { buildingName, aulaName } = params
  const edificio = campus.edificios.find(e => e.nombre === urlToStr(buildingName!))
  const aula = edificio?.aulas.find(a => a.nombre === urlToStr(aulaName!)) ?? null
  return aula
}
