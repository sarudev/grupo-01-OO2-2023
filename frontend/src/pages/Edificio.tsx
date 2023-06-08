import { useLoaderData } from 'react-router-dom'
import { firstUpper } from '../utils/utils'
import { type Edificio as IEdificio } from '../types/types'
import NotFound from './NotFound'
import { useCallback } from 'react'
import { lugarDependencia } from '../types/enums'
import Lugar from '../components/Lugar'
import Tabs from '../components/Tabs'
import '../styles/edificio.scss'

export default function Edificio () {
  const building = useLoaderData() as IEdificio

  if (building == null) return <NotFound />

  const TabsElem = useCallback(function TabsElem () {
    let tabs = ['sensores', 'historial']
    const dependencia = lugarDependencia[building.tipo].length > 0 ? [lugarDependencia[building.tipo], ...tabs] : null

    tabs = dependencia ?? tabs

    return <Tabs tabsNames={tabs}/>
  }, [])

  const ContentElem = useCallback(function TabsElem () {
    return <></>
  }, [])

  return (
    <Lugar
      svgName={building.tipo}
      title={firstUpper(building.tipo)}
      description={building.nombre}
      tabs={TabsElem}
      content={ContentElem}
    />
  )
}
