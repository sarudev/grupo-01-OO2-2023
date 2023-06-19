import { Navigate, useLoaderData, useLocation } from 'react-router-dom'
import NotFound from './NotFound'
import '../styles/lugar.scss'
import { Routes } from '../types/enums'
import Top from '../components/lugar/Top'
import Bot from '../components/lugar/Bot'
import { type LoaderResponse } from '../types/types'

export default function Lugar () {
  const { lugar, status, userRole, serverWorking } = useLoaderData() as LoaderResponse
  const { pathname } = useLocation()

  if (status === 401 || userRole == null) return <Navigate to={Routes.Login} replace state={{ from: pathname }} />
  if (status === 500 || !serverWorking) return <Navigate to={'/'} replace />
  if (lugar == null) return <NotFound />

  return (
    <div className='container'>
      <Top lugar={lugar} userRole={userRole} />
      <Bot lugar={lugar} userRole={userRole} />
    </div>
  )
}
