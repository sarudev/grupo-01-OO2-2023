import { useLayoutEffect, useMemo } from 'react'
import { useAppDispatch, useAppSelector } from '../../hooks/Redux.'
import useLugarDependencia from '../../hooks/useLugarDependencia'
import { type UserRole } from '../../types/enums'
import { type Lugares } from '../../types/types'
import { isEdificio, isParking } from '../../types/typeguards'
import { setSensores } from '../../redux/reducer/sensores'
import { setHistorial } from '../../redux/reducer/historial'
import { setDependencias } from '../../redux/reducer/dependencias'
import Tabs from './bot/Tabs'
import Dependencias from './bot/Dependencias'
import Sensores from './bot/Sensores'
import Historial from './bot/Historial'
import '../../styles/lugar/bot.scss'
import Estado from './bot/Estado'

export default function Bot ({ lugar, userRole }: { lugar: Lugares, userRole: UserRole }) {
  const currentTab = useAppSelector(s => s.currentTab)
  const dispatch = useAppDispatch()
  const { withoutS: nombreDependencia } = useLugarDependencia(lugar.tipo)

  const isEstado = useMemo(() => currentTab != null && currentTab === 'estado', [currentTab])
  const isDependencia = useMemo(() => currentTab === nombreDependencia, [currentTab])
  const isSensor = useMemo(() => currentTab === 'sensores', [currentTab])
  const isHistorial = useMemo(() => currentTab === 'historial sensores', [currentTab])

  useLayoutEffect(() => {
    const deps = isEdificio(lugar) ? lugar.aulas : isParking(lugar) ? lugar.estacionamientos : null
    dispatch(setDependencias(deps))
    dispatch(setSensores(lugar.sensores))
    dispatch(setHistorial(lugar.historial))
  }, [])

  return (
    <div className="bot">
      <nav>
        <Tabs tabsNames={['estado', nombreDependencia, 'sensores', 'historial sensores']} />
      </nav>
      <div className="content">
        <Estado lugar={lugar} visible={isEstado} />
        <Dependencias lugar={lugar} userRole={userRole} visible={isDependencia} />
        <Sensores lugar={lugar} userRole={userRole} visible={isSensor} />
        <Historial lugar={lugar} visible={isHistorial} />
      </div>
    </div>
  )
}
