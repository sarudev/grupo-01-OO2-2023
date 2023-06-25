import { useLayoutEffect } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import '../styles/modalcontent.scss'
import useErrorMessage from '../hooks/useErrorMessage'
import { Routes } from '../types/enums'
import axios, { AxiosError } from 'axios'

export default function Login () {
  const { Message, setError } = useErrorMessage()
  const { state } = useLocation()
  const navigate = useNavigate()

  useLayoutEffect(() => {
    document.title = 'Login'
  }, [])

  const handleSubmit: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault()

    const form = e.currentTarget as HTMLFormElement

    const username = (form[0] as HTMLInputElement).value
    const password = (form[1] as HTMLInputElement).value

    const loginForm = {
      username,
      password
    }

    void axios.post(Routes.BaseUrl + Routes.Login, loginForm, {
      withCredentials: true
    }).then(() => {
      navigate(state?.from ?? '/')
    })
      .catch((err: unknown) => {
        if (err instanceof AxiosError) {
          setError(err.response?.data.error ?? '')
        } else {
          console.error(err)
        }
      })
  }

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <span>Bienvenido</span>
      </div>
      <div className="inputs">
        <div className="container-input">
          <input required className='input' type="text" placeholder='Nombre de usuario' />
        </div>
        <div className="container-input">
          <input required className='input' type="password" placeholder='Contraseña' />
        </div>
      </div>
      <button className="send" type="submit">Iniciar sesión</button>
      <Message />
    </form>
  )
}
