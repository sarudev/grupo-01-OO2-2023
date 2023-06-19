import { useState, useLayoutEffect } from 'react'
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
  const [currentBuilding, setCurrentBuilding] = useState('')
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
        setUserRole(data.role)
        setLoggedIn(true)
      } catch (err: unknown) {
        if (err instanceof AxiosError) {
          if (err.response?.status === 401) {
            setLoggedIn(false)
          }
        }
      }
    }
    void request()
  }, [])

  return (
    <div className="campus-container">
      <MouseCartelito text={currentBuilding} />
      <div className="session">
        {loggedIn ? <span>Sesión iniciada como {userRole}</span> : <span>Sesión no iniciada</span>}
        <Link className="logout" to={loggedIn ? Routes.Logout : Routes.Login}>{loggedIn ? 'Cerrar' : 'Iniciar'} sesión</Link>
      </div>
      <Campus />
    </div>
  )
}
