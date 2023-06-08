import { useLoaderData } from 'react-router-dom'
import { type Aula as IAula } from '../types/types'
import { firstUpper } from '../utils/utils'
import NotFound from './NotFound'
import { useCallback } from 'react'
import { lugarDependencia } from '../types/enums'
import Tabs from '../components/Tabs'
import Lugar from '../components/Lugar'

export default function Aula () {
  const aula = useLoaderData() as IAula
  console.log(aula)
  if (aula == null) return <NotFound />

  const TabsElem = useCallback(function TabsElem () {
    let tabs = ['sensores', 'historial']
    const dependencia = lugarDependencia[aula.tipo].length > 0 ? [lugarDependencia[aula.tipo], ...tabs] : null

    tabs = dependencia ?? tabs

    return <Tabs tabsNames={tabs}/>
  }, [])

  const ContentElem = useCallback(function TabsElem () {
    return <></>
  }, [])

  return (
    <Lugar
      svgName={aula.tipo}
      title={firstUpper(aula.tipo)}
      description={aula.nombre}
      tabs={TabsElem}
      content={ContentElem}
    />
  )
}
