import { useNavigate } from 'react-router-dom'
import { useLayoutEffect } from 'react'
import axios from 'axios'
import { Routes } from '../types/enums'
import '../styles/logout.scss'

export default function Logout () {
  const navigate = useNavigate()

  useLayoutEffect(() => {
    const logout = async () => {
      await axios(Routes.BaseUrl + Routes.Logout, { withCredentials: true })
      navigate('/')
    }
    void logout()
    document.title = 'Logout'
  }, [])

  return (
    <div className='logout-message'>
      Loging out...
    </div>
  )
}
