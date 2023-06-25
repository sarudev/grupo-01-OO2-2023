import { useState, useLayoutEffect, useCallback } from 'react'
import { ReactComponent as Campus } from '../assets/campus.svg'
import useBuildingSelector from '../hooks/useBuildingSelector'
import useBuildingNameSelector from '../hooks/useBuildingNameSelector'
import useBuildingNavigation from '../hooks/useBuildingNavigation'
import useCartelitowo from '../hooks/useCarteliowo'
import MouseCartelito from '../components/Cartelitowo'
import '../styles/campus.scss'
import { Link } from 'react-router-dom'
import { Routes, type UserRole } from '../types/enums'
import axios, { AxiosError } from 'axios'
import { type UserData } from '../types/types'

export default function Index () {
  const [currentBuilding, setCurrentBuilding] = useState({ type: '---', name: '---' })
  const [serverWorking, setServerWorking] = useState<boolean | null>(null)
  const [userRole, setUserRole] = useState<UserRole | null>(null)
  const [loggedIn, setLoggedIn] = useState<boolean>(false)
  useBuildingSelector()
  useBuildingNameSelector()
  useCartelitowo('cartelitowo', setCurrentBuilding)

  useBuildingNavigation()

  useLayoutEffect(() => {
    const request = async () => {
      try {
        const { data } = await axios.get(Routes.BaseUrl + Routes.UserData, { withCredentials: true }) as { data: UserData }
        setServerWorking(true)
        setLoggedIn(true)
        setUserRole(data.role)
      } catch (err: unknown) {
        if (err instanceof AxiosError) {
          if (err.response?.status === 401) {
            setServerWorking(true)
            setLoggedIn(false)
          } else if (err.code === 'ERR_NETWORK') {
            setServerWorking(false)
            setLoggedIn(false)
          }
        }
      }
    }
    void request()
    document.title = 'Campus'
  }, [])

  const LoginSession = useCallback(() => {
    return (
      <>
        {loggedIn ? <span>Sesión iniciada como {userRole}</span> : <span>Sesión no iniciada</span>}
        <Link className="logout" to={loggedIn ? Routes.Logout : Routes.Login}>{loggedIn ? 'Cerrar' : 'Iniciar'} sesión</Link>
      </>
    )
  }, [loggedIn])

  return (
    <div className="campus-container">
      <MouseCartelito text={currentBuilding.name} lugar={currentBuilding.type} />
      <div className="session">
        {serverWorking == null ? <span>Cargando...</span> : serverWorking ? <LoginSession /> : <span>El servidor no está funcionando.</span>}
      </div>
      <Campus />
    </div>
  )
}
