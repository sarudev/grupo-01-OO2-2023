import { type LoaderFunctionArgs, useLoaderData } from 'react-router-dom'
import { type Status, type Aula as IAula } from '../types/types'
import campus from '../assets/campus'
import { urlToStr } from '../utils/utils'
import useInvalidDynamicPage from '../hooks/useInvalidDynamicPage'
import NotFound from './NotFound'
import { statusMessage } from '../types/enums'

export default function Aula () {
  const aula = useLoaderData() as LoaderData

  const notFound = useInvalidDynamicPage(aula.status)
  if (notFound) return <NotFound />

  return (
    <div>
      {JSON.stringify(aula, null, 2)}
    </div>
  )
}

export async function loader ({ params }: LoaderFunctionArgs) {
  const { buildingName, aulaName } = params
  const edificio = campus.edificios.find(e => e.nombre === urlToStr(buildingName!))
  const aula = edificio?.aulas.find(a => a.nombre === urlToStr(aulaName!))
  const statusCode = edificio == null || aula == null ? 404 : 200
  return { status: { code: statusCode, message: statusMessage[statusCode] }, aula } as LoaderData
}

interface LoaderData {
  status: Status
  aula: IAula | null
}
