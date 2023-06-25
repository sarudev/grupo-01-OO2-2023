import { useLayoutEffect, useState } from 'react'
import '../styles/notfound.scss'
import { Navigate } from 'react-router-dom'
import { Routes } from '../types/enums'
import axios from 'axios'

export default function Status () {
  const [loadedDB, setLoadedDB] = useState(false)

  useLayoutEffect(() => {
    document.title = 'Loding db...'

    const load = async () => {
      await axios.get(`${Routes.BaseUrl}${Routes.LoadDB}`)
      setLoadedDB(true)
    }
    void load()
  }, [])

  return (
    <div className="not-found">
      <div className="code">Oops...</div>
      <div className="message">
        Estamos inicializando la base de datos...
        <br/>
        Al terminar serás redireccionado.
      </div>
      {loadedDB && <Navigate to={Routes.Campus} />}
    </div>
  )
}
