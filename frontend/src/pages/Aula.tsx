import { useLoaderData } from 'react-router-dom'
import { type IAula } from '../types/types'
import NotFound from './NotFound'
import Lugar from '../components/Lugar'

export default function Aula () {
  const aula = useLoaderData() as IAula

  if (aula == null) return <NotFound />

  return <Lugar lugar={aula} />
}
