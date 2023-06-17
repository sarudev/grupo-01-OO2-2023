import { useLocation, useNavigate } from 'react-router-dom'
import '../styles/modalcontent.scss'
import useErrorMessage from '../hooks/useErrorMessage'
const { VITE_API_URL } = import.meta.env as Record<string, string>

export default function Login () {
  const { Message, setError } = useErrorMessage()
  const { state } = useLocation()
  const navigate = useNavigate()

  const handleSubmit: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault()

    const form = e.currentTarget as HTMLFormElement

    const username = (form[0] as HTMLInputElement).value
    const password = (form[1] as HTMLInputElement).value

    const loginForm = {
      username,
      password
    }
    void fetch('http://localhost:5282/login', {
      method: 'post',
      credentials: 'include',
      headers: {
        'Access-Control-Allow-Origin': 'http://localhost:5173/',
        'content-type': 'application/json'
      },
      body: JSON.stringify(loginForm)
    })
      .then(async res => await res.json()
        .then(res => console.log(res))
      )
    // void fetch('http://localhost:5282/login', {
    //   method: 'post',
    //   credentials: 'include',
    //   headers: {
    //     'Access-Control-Allow-Origin': 'http://localhost:5173/',
    //     'content-type': 'application/json'
    //   },
    //   body: JSON.stringify(loginForm)
    // })
    //   .then(res => {
    //     console.log(res)
    //     console.log(res.headers)

    //     void res.json().then(res => {
    //       console.log(res)
    //     })
    //     // navigate(state?.from ?? '/')
    //   })
    //   .catch((err: unknown) => {
    //     console.log(err)
    //     // const data = err.response?.data as { error: string }
    //     // if (data.error != null) setError(data.error)
    //     // console.error(err)
    //   })
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
