import { useLoaderData } from 'react-router-dom'
import { type Lugar as ILugar, type Edificio as IEdificio } from '../types/types'
import NotFound from './NotFound'
import Lugar from '../components/Lugar'

export default function Edificio () {
  const building = useLoaderData() as IEdificio & { lugar: ILugar | null }

  if (building == null) return <NotFound />

  return <Lugar lugar={building} />
}
