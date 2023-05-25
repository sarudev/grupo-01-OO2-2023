import { type LoaderFunctionArgs, useLoaderData } from 'react-router-dom'
import campus from '../assets/campus'
import { urlToStr } from '../utils/utils'
import { type Status, type Edificio as IEdificio } from '../types/types'
import { statusMessage } from '../types/enums'
import useInvalidDynamicPage from '../hooks/useInvalidDynamicPage'
import NotFound from './NotFound'

export default function Edificio () {
  const building = useLoaderData() as LoaderData

  const notFound = useInvalidDynamicPage(building.status)
  if (notFound) return <NotFound />

  return (
    <div>
      {JSON.stringify(building, null, 2)}
    </div>
  )
}

export async function loader ({ params }: LoaderFunctionArgs) {
  const { buildingName } = params
  const edificio = campus.edificios.find(e => e.nombre === urlToStr(buildingName!))
  const statusCode = edificio == null ? 404 : 200
  return { status: { code: statusCode, message: statusMessage[statusCode] }, edificio } as LoaderData
}

interface LoaderData {
  status: Status
  edificio: IEdificio | null
}
