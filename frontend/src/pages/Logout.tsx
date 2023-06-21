import { useNavigate } from 'react-router-dom'
import { useEffect } from 'react'
import axios from 'axios'
import { Routes } from '../types/enums'
import '../styles/logout.scss'

export default function Logout () {
  const navigate = useNavigate()

  useEffect(() => {
    const logout = async () => {
      await axios(Routes.BaseUrl + Routes.Logout, { withCredentials: true })
      navigate('/')
    }
    void logout()
  }, [])

  return (
    <div className='logout-message'>
      Loging out...
    </div>
  )
}
