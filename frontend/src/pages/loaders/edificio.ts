import { type LoaderFunctionArgs } from 'react-router-dom'
import campus from '../../assets/campus'
import { urlToStr } from '../../utils/utils'

export default async function edificioLoader ({ params }: LoaderFunctionArgs) {
  const { buildingName } = params
  const edificio = campus.edificios.find(e => e.nombre === urlToStr(buildingName!)) ?? null
  return edificio
}
