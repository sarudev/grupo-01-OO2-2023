import { useLoaderData, useLocation } from 'react-router-dom'
import Status from './Status'
import '../styles/lugar.scss'
import { Routes } from '../types/enums'
import Top from '../components/lugar/Top'
import Bot from '../components/lugar/Bot'
import { type LoaderResponse } from '../types/types'

export default function Lugar () {
  const { lugar, status, userRole, serverWorking } = useLoaderData() as LoaderResponse
  const { pathname } = useLocation()
  console.log(status)

  if (status === 500 || !serverWorking) return <Status code={status} statusMessage='El servidor no está funcionando' goto={'/'} gotoMessage='Volver al campus' />
  if (status === 401 || userRole == null) return <Status code={status} statusMessage='No haz iniciado sesión' goto={Routes.Login} gotoState={pathname} gotoMessage='Iniciar sesión' />
  if (status === 404 || lugar == null) return <Status code={status} statusMessage='Página no encontrada' goto='/' gotoMessage='Volver al campus' />

  return (
    <div className='container'>
      <Top lugar={lugar} userRole={userRole} />
      <Bot lugar={lugar} userRole={userRole} />
    </div>
  )
}
