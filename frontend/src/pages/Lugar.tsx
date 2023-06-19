import { useMemo, useLayoutEffect } from 'react'
import Tabs from '../components/Tabs'
import { type Lugares, type LoaderResponse } from '../types/types'
import Dependencias from '../components/Dependencias'
import Sensores from '../components/Sensores'
import Historial from '../components/Historial'
import { Navigate, useLoaderData, useLocation } from 'react-router-dom'
import { useAppDispatch, useAppSelector } from '../hooks/Redux.'
import { setDependencias } from '../redux/reducer/dependencias'
import { setSensores } from '../redux/reducer/sensores'
import { setHistorial } from '../redux/reducer/historial'
import { isEdificio, isParking } from '../types/typeguards'
import useLugarDependencia from '../hooks/useLugarDependencia'
import NotFound from './NotFound'
import '../styles/lugar.scss'
import { Routes, type UserRole } from '../types/enums'
import LugarTop from '../components/LugarTop'
import LugarBot from '../components/LugarBot'

export default function Lugar () {
  const { lugar, status, userRole, serverWorking } = useLoaderData() as LoaderResponse
  const { pathname } = useLocation()

  if (status === 401 || userRole == null) return <Navigate to={Routes.Login} replace state={{ from: pathname }} />
  if (status === 500 || !serverWorking) return <NotFound />
  if (lugar == null) return <NotFound />

  return (
    <div className='container'>
      <LugarTop lugar={lugar} userRole={userRole} />
      <LugarBot lugar={lugar} userRole={userRole} />
    </div>
  )
}
