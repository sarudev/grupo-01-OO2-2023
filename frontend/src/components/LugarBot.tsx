import { useLayoutEffect, useMemo } from 'react'
import { useAppDispatch, useAppSelector } from '../hooks/Redux.'
import useLugarDependencia from '../hooks/useLugarDependencia'
import { type UserRole } from '../types/enums'
import { type Lugares } from '../types/types'
import { isEdificio, isParking } from '../types/typeguards'
import { setSensores } from '../redux/reducer/sensores'
import { setHistorial } from '../redux/reducer/historial'
import { setDependencias } from '../redux/reducer/dependencias'
import Tabs from './Tabs'
import Dependencias from './Dependencias'
import Sensores from './Sensores'
import Historial from './Historial'

export default function LugarBot ({ lugar, userRole }: { lugar: Lugares, userRole: UserRole }) {
  const currentTab = useAppSelector(s => s.currentTab)
  const dispatch = useAppDispatch()
  const { withoutS: nombreDependencia } = useLugarDependencia(lugar.tipo)

  const isDependencia = useMemo(() => currentTab != null && currentTab === nombreDependencia, [currentTab])
  const isSensor = useMemo(() => currentTab === 'sensores', [currentTab])
  const isHistorial = useMemo(() => currentTab === 'historial', [currentTab])

  useLayoutEffect(() => {
    const deps = isEdificio(lugar) ? lugar.aulas : isParking(lugar) ? lugar.estacionamientos : null
    dispatch(setDependencias(deps))
    dispatch(setSensores(lugar.sensores))
    console.log(lugar)
    dispatch(setHistorial(lugar.historial))
  }, [])

  return (
    <div className="bot">
      <nav>
        <Tabs tabsNames={['estado', nombreDependencia, 'sensores', 'historiales sensores']} />
      </nav>
      <div className="content">
        {isDependencia && <Dependencias lugar={lugar} userRole={userRole} visible={isDependencia} />}
        {isSensor && <Sensores lugar={lugar} userRole={userRole} visible={isSensor} />}
        {isHistorial && <Historial visible={isHistorial} />}
      </div>
  </div>
  )
}
