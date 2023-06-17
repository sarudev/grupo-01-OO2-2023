import { useMemo, useLayoutEffect } from 'react'
import Icon from '../components/Icon'
import Tabs from '../components/Tabs'
import { type LoaderResponse, type Lugares } from '../types/types'
import { firstUpper } from '../utils/utils'
import Dependencias from '../components/Dependencias'
import Sensores from '../components/Sensores'
import Historial from '../components/Historial'
import { Link, Navigate, useLoaderData, useLocation } from 'react-router-dom'
import { useAppDispatch, useAppSelector } from '../hooks/Redux.'
import { setDependencias } from '../redux/reducer/dependencias'
import { setSensores } from '../redux/reducer/sensores'
import { setHistorial } from '../redux/reducer/historial'
import { isEdificio, isParking } from '../types/typeguards'
import useLugarDependencia from '../hooks/useLugarDependencia'
import Modal from '../components/Modal'
import Login from './Login'
import NotFound from './NotFound'
import '../styles/lugar.scss'

export default function Lugar () {
  const { lugar, status, userRole, serverWorking } = useLoaderData() as LoaderResponse
  const { pathname } = useLocation()

  if (status === 401 || userRole == null) return <Navigate to='/login' replace state={{ from: pathname }} />
  if (status === 500 || !serverWorking) return <NotFound />
  if (lugar == null) return <NotFound />

  const currentTab = useAppSelector(s => s.currentTab)
  const { withoutS: nombreDependencia } = useLugarDependencia(lugar.tipo)
  const dispatch = useAppDispatch()

  useLayoutEffect(() => {
    const deps = isEdificio(lugar) ? lugar.aulas : isParking(lugar) ? lugar.estacionamientos : null
    dispatch(setDependencias(deps))
    dispatch(setSensores(lugar.sensores))
    console.log(lugar)
    dispatch(setHistorial(lugar.historial))
  }, [])

  const isDependencia = useMemo(() => currentTab != null && currentTab === nombreDependencia, [currentTab])
  const isSensor = useMemo(() => currentTab === 'sensores', [currentTab])
  const isHistorial = useMemo(() => currentTab === 'historial', [currentTab])

  return (
    <div className='container'>
      <div className="top">
        <div className="icon">
          <Icon svgName={lugar.tipo} />
        </div>
        <div className="text">
          <div className="text-container">
            <div className="title">{firstUpper(lugar.tipo)}</div>
            <div className="description">{lugar.nombre}</div>
          </div>
          <Link className="back" to={lugar.lugar == null ? '/' : `/${lugar.lugar.tipo}/${lugar.lugar.nombre}`}>Volver a {lugar?.lugar?.nombre ?? 'Campus'}</Link>
        </div>
      </div>
      <div className="bot">
        <nav>
          <Tabs tabsNames={[nombreDependencia, 'sensores', 'historial']} />
        </nav>
        <div className="content">
          {isDependencia && <Dependencias lugar={lugar} />}
          {isSensor && <Sensores lugar={lugar} />}
          {isHistorial && <Historial lugar={lugar} />}
        </div>
      </div>
      <Modal>
        <Login />
      </Modal>
    </div>
  )
}
