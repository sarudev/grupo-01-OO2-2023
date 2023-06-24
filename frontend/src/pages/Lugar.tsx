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

  if (status === 404) return <Status code={404} statusMessage='Página no encontrada' goto='/' gotoMessage='Volver al campus' />
  if (status === 500) return <Status code={500} statusMessage='El servidor no está funcionando' goto={'/'} gotoMessage='Volver al campus' />
  if (status === 401) return <Status code={401} statusMessage='No haz iniciado sesión' goto={Routes.Login} gotoState={pathname} gotoMessage='Iniciar sesión' />

  return (
    <div className='container'>
      <Top lugar={lugar!} userRole={userRole!} />
      <Bot lugar={lugar!} userRole={userRole!} />
    </div>
  )
}
